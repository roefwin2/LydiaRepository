package com.example.lydiacontactsapp.data.models


import com.squareup.moshi.Json

data class LydiaResults(
    @Json(name = "info")
    val info: Info,
    @Json(name = "results")
    val results: List<Result>
)