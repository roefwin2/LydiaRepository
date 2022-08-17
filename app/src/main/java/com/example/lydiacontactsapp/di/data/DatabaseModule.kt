package com.example.lydiacontactsapp.di.data

import android.content.Context
import androidx.room.Room
import com.example.lydiacontactsapp.data.local.database.LydiaContactsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideLydiaContactsDatabase(
        @ApplicationContext context: Context
    ): LydiaContactsDatabase {
        return Room.databaseBuilder(
            context,
            LydiaContactsDatabase::class.java,
            "lydiadb.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}