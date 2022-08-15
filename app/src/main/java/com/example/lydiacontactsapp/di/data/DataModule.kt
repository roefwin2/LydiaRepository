package com.example.lydiacontactsapp.di.data

import com.example.lydiacontactsapp.data.remote.LydiaContactsApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideRetrofitApi(
        moshi: Moshi
    ) : LydiaContactsApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://randomuser.me/api/1.3/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        return retrofit.create(LydiaContactsApi::class.java)
    }

    @Provides
    fun provideMoshi() = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}