package com.example.lydiacontactsapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lydiacontactsapp.data.local.entity.RemoteKeysEntity

@Dao
interface RemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey : List<RemoteKeysEntity>)

    @Query("SELECT * FROM remotekeys WHERE contactId = :id")
    suspend fun remoteKeysContactId(id :String) : RemoteKeysEntity?

    @Query("DELETE FROM remotekeys")
    suspend fun clearRemoteKeys()

}