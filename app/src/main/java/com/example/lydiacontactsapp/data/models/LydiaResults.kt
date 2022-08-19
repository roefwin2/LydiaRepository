package com.example.lydiacontactsapp.data.models


import com.example.lydiacontactsapp.data.local.entity.LydiaContactEntity
import com.example.lydiacontactsapp.data.models.Result.Companion.toLydiaContactEntity
import com.squareup.moshi.Json

data class LydiaResults(
    @Json(name = "info")
    val info: Info,
    @Json(name = "results")
    val results: List<Result>
) {
    companion object {
        fun LydiaResults.toLydiaContactsEntity(): List<LydiaContactEntity> {
            return results.map { it.toLydiaContactEntity() }
        }
    }
}