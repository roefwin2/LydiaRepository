package com.example.lydiacontactsapp.presenter


import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.lydiacontactsapp.data.local.entity.LydiaContactEntity
import com.example.lydiacontactsapp.data.pagination.LydiaContractPagination
import com.example.lydiacontactsapp.domain.models.LydiaContact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
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


data class ScreenState(
    val page: Int,
    val contacts: List<LydiaContact>
)