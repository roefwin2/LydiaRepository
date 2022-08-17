package com.example.lydiacontactsapp.presenter


import androidx.lifecycle.ViewModel
import androidx.paging.*
import com.example.lydiacontactsapp.data.local.entity.LydiaContactEntity
import com.example.lydiacontactsapp.data.pagination.LydiaContractPagination
import com.example.lydiacontactsapp.domain.models.LydiaContact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject


@HiltViewModel
@ExperimentalPagingApi
class MainActivityViewModel
@Inject constructor(
    private val lydiaContractPagination: LydiaContractPagination
) : ViewModel() {

    val state: Flow<PagingData<LydiaContactEntity>> =
        Pager(PagingConfig(pageSize = 20), remoteMediator = lydiaContractPagination) {
            lydiaContractPagination.contactsDao.getLydiaContacts()
        }.flow.catch {
            println(it.message)
        }

}
