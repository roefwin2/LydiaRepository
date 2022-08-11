package com.example.lydiacontactsapp.di

import com.example.lydiacontactsapp.data.remote.LydiaContactsApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideRetrofitApi(
        moshi: Moshi
    ) : LydiaContactsApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://randomuser.me/api/1.3/?seed=lydia")
            .build()
        return retrofit.create(LydiaContactsApi::class.java)
    }

}