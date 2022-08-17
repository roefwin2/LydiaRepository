package com.example.lydiacontactsapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remotekeys")
data class RemoteKeysEntity(
    @PrimaryKey val contactId : String,
    val prevKey : Int?,
    val nextKey : Int?,
)
