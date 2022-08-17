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
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                // In this example, you never need to prepend, since REFRESH
                // will always load the first page in the list. Immediately
                // return, reporting end of pagination.
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                // Get the last User object id for the next RemoteKey.
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = true
                        )

                    // You must explicitly check if the last item is null when
                    // appending, since passing null to networkService is only
                    // valid for initial load. If lastItem is null it means no
                    // items were loaded after the initial REFRESH and there are
                    // no more items to load.

                    lastItem.id
                }
            }

            // Suspending network load via Retrofit. This doesn't need to
            // be wrapped in a withContext(Dispatcher.IO) { ... } block
            // since Retrofit's Coroutine CallAdapter dispatches on a
            // worker thread.
            val response = lydiaContactsApi.getContacts(20,loadKey?.div(20) ?: 1 )

            // Store loaded data, and next key in transaction, so that
            // they're always consistent.
            dataBase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    contactsDao.clearAll()
                }

                // Insert new users into database, which invalidates the
                // current PagingData, allowing Paging to present the updates
                // in the DB.
                contactsDao.insertAll(response.toLydiaContactsEntity())
            }

            // End of pagination has been reached if no users are returned from the
            // service
            MediatorResult.Success(
                endOfPaginationReached = response.results.isEmpty()
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
    ): Int? {
        return when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getClosestRemoteKey(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }
            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state)
                remoteKeys?.nextKey
            }
            LoadType.PREPEND -> {
                val remoteKeys = getFirstRemoteKey(state)
                remoteKeys?.prevKey
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
            .firstOrNull() { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { contact -> dataBase.keysDao.remoteKeysContactId(contact.id.toString()) }
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