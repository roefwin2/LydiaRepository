package com.example.lydiacontactsapp.data.models


import com.squareup.moshi.Json

data class Dob(
    @Json(name = "age")
    val age: Int,
    @Json(name = "date")
    val date: String
)