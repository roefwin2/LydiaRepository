package com.example.lydiacontactsapp.di

import androidx.paging.ExperimentalPagingApi
import com.example.lydiacontactsapp.data.repository.LydiaContactsRepositoryImpl
import com.example.lydiacontactsapp.domain.repository.LydiaContactsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @ExperimentalPagingApi
    @Binds
    abstract fun bindLydiaContactsRepository(
        lydiaContactsRepositoryImpl: LydiaContactsRepositoryImpl
    ): LydiaContactsRepository
}