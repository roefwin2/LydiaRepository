package com.example.lydiacontactsapp.domain.usecases

import androidx.paging.PagingData
import com.example.lydiacontactsapp.domain.models.LydiaContact
import com.example.lydiacontactsapp.domain.repository.LydiaContactsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface GetLydiaContactListUseCase {
    suspend fun invoke(): Flow<PagingData<LydiaContact>>
}


class GetLydiaContactListUseCaseImpl @Inject constructor(
    private val lydiaContactsRepository: LydiaContactsRepository
) : GetLydiaContactListUseCase {
    override suspend fun invoke(): Flow<PagingData<LydiaContact>>
    {
        return lydiaContactsRepository.getContacts().map {
            it
        }
    }
}