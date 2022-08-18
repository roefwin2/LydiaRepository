package com.example.lydiacontactsapp.data.pagination

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.lydiacontactsapp.data.local.database.LydiaContactsDatabase
import com.example.lydiacontactsapp.data.local.entity.LydiaContactEntity
import com.example.lydiacontactsapp.data.local.entity.RemoteKeysEntity
import com.example.lydiacontactsapp.data.models.LydiaResults.Companion.toLydiaContactsEntity
import com.example.lydiacontactsapp.data.remote.LydiaContactsApi
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@ExperimentalPagingApi
class LydiaContractPagination @Inject constructor(
    private val lydiaContactsApi: LydiaContactsApi,
    private val dataBase: LydiaContactsDatabase
) : RemoteMediator<Int, LydiaContactEntity>() {

    val contactsDao = dataBase.dao
    val keysDao = dataBase.keysDao

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, LydiaContactEntity>
    ): MediatorResult {
        return try {
            // The network load method takes an optional String
            // parameter. For every page after the first, pass the String
            // token returned from the previous page to let it continue
            // from where it left off. For REFRESH, pass null to load the
            // first page.
            val page = when (val key = getKeyPageData(loadType,state)) {
                is MediatorResult.Success -> {
                    return key
                }
                else -> key as Int
            }

            // Suspending network load via Retrofit. This doesn't need to
            // be wrapped in a withContext(Dispatcher.IO) { ... } block
            // since Retrofit's Coroutine CallAdapter dispatches on a
            // worker thread.
            val response = lydiaContactsApi.getContacts(20, page)
            val endReached = response.results.isEmpty()
            // Store loaded data, and next key in transaction, so that
            // they're always consistent.
            dataBase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    keysDao.clearRemoteKeys()
                    contactsDao.clearAll()
                }

                val prevKey = if(page == 1) null else page.minus(1)
                val nextKey = if(endReached) null else page.plus(1)
                val keys = response.toLydiaContactsEntity().map {
                    RemoteKeysEntity(it.id.toString(), prevKey, nextKey)
                }

                keysDao.insertAll(keys)
                // Insert new users into database, which invalidates the
                // current PagingData, allowing Paging to present the updates
                // in the DB.
                contactsDao.insertAll(response.toLydiaContactsEntity())
            }

            // End of pagination has been reached if no users are returned from the
            // service
            MediatorResult.Success(
                endOfPaginationReached = endReached
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    /**
     * this returns the page key or the final end of list success result
     */
    private suspend fun getKeyPageData(
        loadType: LoadType,
        state: PagingState<Int, LydiaContactEntity>
    ): Any? {
        return when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getClosestRemoteKey(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }
            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state)?: return MediatorResult.Success(endOfPaginationReached = false)
                remoteKeys.nextKey ?: return MediatorResult.Success(endOfPaginationReached = true)
                remoteKeys.nextKey
            }
            LoadType.PREPEND -> {
                val remoteKeys = getFirstRemoteKey(state) ?: return MediatorResult.Success(endOfPaginationReached = false)
                remoteKeys.prevKey ?: return MediatorResult.Success(endOfPaginationReached = true)
                remoteKeys.prevKey
            }
        }
    }


    /**
     * get the last remote key inserted which had the data
     */
    private suspend fun getLastRemoteKey(state: PagingState<Int, LydiaContactEntity>): RemoteKeysEntity? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { contact -> dataBase.keysDao.remoteKeysContactId(contact.id.toString()) }
    }

    /**
     * get the first remote key inserted which had the data
     */
    private suspend fun getFirstRemoteKey(state: PagingState<Int, LydiaContactEntity>): RemoteKeysEntity? {
        return state.pages
            .firstOrNull{
                it.data.isNotEmpty()
            }?.data?.firstOrNull()?.let {
                    contact -> keysDao.remoteKeysContactId(contact.id.toString())
            }
    }

    /**
     * get the closest remote key inserted which had the data
     */
    private suspend fun getClosestRemoteKey(state: PagingState<Int, LydiaContactEntity>): RemoteKeysEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { contactId ->
                dataBase.keysDao.remoteKeysContactId(contactId.toString())
            }
        }
    }
}