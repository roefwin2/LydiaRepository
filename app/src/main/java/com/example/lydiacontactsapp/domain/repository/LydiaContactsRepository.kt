package com.example.lydiacontactsapp.domain.repository

import com.example.lydiacontactsapp.domain.models.LydiaContact
import com.example.lydiacontactsapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface LydiaContactsRepository {

    fun getContacts(results: Int, page: Int): Flow<Resource<Pair<Int, List<LydiaContact>>>>
}