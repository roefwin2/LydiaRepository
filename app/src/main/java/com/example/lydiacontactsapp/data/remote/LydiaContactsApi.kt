package com.example.lydiacontactsapp.data.remote

import com.example.lydiacontactsapp.data.models.LydiaResults
import retrofit2.http.GET
import retrofit2.http.Query

interface LydiaContactsApi {

    @GET("?seed=lydia")
    suspend fun getContacts(@Query("results") results : Int , @Query("page") page : Int) : LydiaResults
}