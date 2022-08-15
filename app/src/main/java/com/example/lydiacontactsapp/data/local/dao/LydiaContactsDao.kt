package com.example.lydiacontactsapp.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.lydiacontactsapp.data.local.entity.LydiaContactEntity

@Dao
interface LydiaContactsDao {

    @Query(
        "SELECT * FROM contacts"
    )
    fun getLydiaContacts(query: String): List<LydiaContactEntity>
}