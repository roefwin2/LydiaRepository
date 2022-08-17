package com.example.lydiacontactsapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lydiacontactsapp.data.local.entity.LydiaContactEntity

@Dao
interface LydiaContactsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(contactsEntity : List<LydiaContactEntity>)

    @Query("SELECT * FROM contacts")
    fun getLydiaContacts(): PagingSource<Int,LydiaContactEntity>

    @Query("DELETE FROM contacts")
    suspend fun clearAll()
}