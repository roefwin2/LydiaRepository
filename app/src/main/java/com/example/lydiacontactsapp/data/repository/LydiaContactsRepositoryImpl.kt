package com.example.lydiacontactsapp.data.repository

import com.example.lydiacontactsapp.data.models.LydiaResults.Companion.toPageContactsPair
import com.example.lydiacontactsapp.data.remote.LydiaContactsApi
import com.example.lydiacontactsapp.domain.models.LydiaContact
import com.example.lydiacontactsapp.domain.repository.LydiaContactsRepository
import com.example.lydiacontactsapp.utils.Error
import com.example.lydiacontactsapp.utils.Loading
import com.example.lydiacontactsapp.utils.Resource
import com.example.lydiacontactsapp.utils.Success
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class LydiaContactsRepositoryImpl @Inject constructor(
    private val lydiaContactsApi: LydiaContactsApi
) : LydiaContactsRepository {


    override fun getContacts(results: Int, page: Int) = flow<Resource<Pair<Int,List<LydiaContact>>>> {
        emit(Loading())
        try {
            val response = lydiaContactsApi.getContacts(results, page)
            emit(Success(response.toPageContactsPair()))
        } catch (e: Exception) {
            emit(Error(e.toString()))
        }
    }
}