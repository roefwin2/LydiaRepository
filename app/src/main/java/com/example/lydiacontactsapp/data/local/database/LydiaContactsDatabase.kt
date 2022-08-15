package com.example.lydiacontactsapp.data.local.database

import androidx.room.Database
import com.example.lydiacontactsapp.data.local.dao.LydiaContactsDao
import com.example.lydiacontactsapp.data.local.entity.LydiaContactEntity

@Database(
    entities = [LydiaContactEntity::class],
    version = 1
)
abstract class LydiaContactsDatabase {
    abstract val dao : LydiaContactsDao
}