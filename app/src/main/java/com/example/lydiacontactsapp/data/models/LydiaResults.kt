package com.example.lydiacontactsapp.data.models


import com.example.lydiacontactsapp.data.models.Result.Companion.toLydiaContact
import com.example.lydiacontactsapp.domain.models.LydiaContact
import com.squareup.moshi.Json

data class LydiaResults(
    @Json(name = "info")
    val info: Info,
    @Json(name = "results")
    val results: List<Result>
) {
    companion object {
        fun LydiaResults.toPageContactsPair(): Pair<Int, List<LydiaContact>> {
            val list = results.map { it.toLydiaContact() }
            return Pair(info.page, list)
        }
    }
}