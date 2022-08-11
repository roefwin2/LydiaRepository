package com.example.lydiacontactsapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface LydiaContactsApi {

    @GET
    suspend fun getContacts(@Query("results") results : Int , @Query("page") page : Int)
}