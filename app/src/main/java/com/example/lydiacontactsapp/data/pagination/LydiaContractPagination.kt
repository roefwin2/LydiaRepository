package com.example.lydiacontactsapp.data.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.lydiacontactsapp.data.models.LydiaResults.Companion.toPageContactsPair
import com.example.lydiacontactsapp.data.remote.LydiaContactsApi
import com.example.lydiacontactsapp.domain.models.LydiaContact
import javax.inject.Inject

class LydiaContractPagination @Inject constructor(
    private val lydiaContactsApi: LydiaContactsApi
) : PagingSource<Int, LydiaContact>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LydiaContact> {
        return try {
            val nextPage = params.key ?: 1
            val result = lydiaContactsApi.getContacts(20, nextPage)
            val contacts = result.toPageContactsPair()
            LoadResult.Page(
                data = contacts.second,
                prevKey = null,
                nextKey = contacts.first + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, LydiaContact>): Int? {
        return null
    }
}