package com.example.lydiacontactsapp.data.models


import com.squareup.moshi.Json

data class Coordinates(
    @Json(name = "latitude")
    val latitude: String,
    @Json(name = "longitude")
    val longitude: String
)