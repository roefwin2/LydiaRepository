package com.example.lydiacontactsapp.data.models


import com.squareup.moshi.Json

data class Id(
    @Json(name = "name")
    val name: String,
    @Json(name = "value")
    val value: String?
)