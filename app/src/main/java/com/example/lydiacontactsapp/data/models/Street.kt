package com.example.lydiacontactsapp.data.models


import com.squareup.moshi.Json

data class Street(
    @Json(name = "name")
    val name: String,
    @Json(name = "number")
    val number: Int
)