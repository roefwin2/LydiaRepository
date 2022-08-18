package com.example.lydiacontactsapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lydiacontactsapp.data.local.dao.LydiaContactsDao
import com.example.lydiacontactsapp.data.local.dao.RemoteKeysDao
import com.example.lydiacontactsapp.data.local.entity.LydiaContactEntity
import com.example.lydiacontactsapp.data.local.entity.RemoteKeysEntity
import dagger.Binds
import dagger.Provides

@Database(
    entities = [LydiaContactEntity::class, RemoteKeysEntity::class],
    exportSchema = false,
    version = 5
)

abstract class LydiaContactsDatabase : RoomDatabase() {
    abstract val dao : LydiaContactsDao
    abstract val keysDao : RemoteKeysDao
}