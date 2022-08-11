package com.example.lydiacontactsapp.domain.usecases

import com.example.lydiacontactsapp.domain.models.LydiaContact
import com.example.lydiacontactsapp.domain.repository.LydiaContactsRepository
import com.example.lydiacontactsapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface GetLydiaContactListUseCase {
    suspend fun invoke(results: Int, page: Int): Flow<Resource<Pair<Int, List<LydiaContact>>>>
}


class GetLydiaContactListUseCaseImpl @Inject constructor(
    private val lydiaContactsRepository: LydiaContactsRepository
) : GetLydiaContactListUseCase {
    override suspend fun invoke(
        results: Int,
        page: Int
    ): Flow<Resource<Pair<Int, List<LydiaContact>>>> {
        return lydiaContactsRepository.getContacts(results, page).map {
            it
        }
    }

}