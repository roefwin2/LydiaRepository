package com.example.lydiacontactsapp.di

import com.example.lydiacontactsapp.domain.usecases.GetLydiaContactListUseCase
import com.example.lydiacontactsapp.domain.usecases.GetLydiaContactListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCasesModule {

    @Binds
    abstract fun bindGetListContactUseCase(
        getLydiaContactListUseCaseImpl: GetLydiaContactListUseCaseImpl
    ) : GetLydiaContactListUseCase
}