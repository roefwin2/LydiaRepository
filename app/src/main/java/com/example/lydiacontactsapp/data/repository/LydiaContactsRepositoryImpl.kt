package com.example.lydiacontactsapp.data.repository

import androidx.paging.*
import com.example.lydiacontactsapp.data.local.entity.LydiaContactEntity.Companion.toLydiaContact
import com.example.lydiacontactsapp.data.pagination.LydiaContractPagination
import com.example.lydiacontactsapp.domain.models.LydiaContact
import com.example.lydiacontactsapp.domain.repository.LydiaContactsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


@ExperimentalPagingApi
class LydiaContactsRepositoryImpl
@Inject constructor(
    private val lydiaContractPagination: LydiaContractPagination
) : LydiaContactsRepository {


    override fun getContacts(): Flow<PagingData<LydiaContact>> =
        Pager(PagingConfig(pageSize = 20), remoteMediator = lydiaContractPagination) {
            lydiaContractPagination.contactsDao.getLydiaContacts()
        }.flow
            .map { data -> data.map { it.toLydiaContact() } }

}