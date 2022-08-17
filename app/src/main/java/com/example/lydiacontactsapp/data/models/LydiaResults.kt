package com.example.lydiacontactsapp.data.models


import android.util.Pair
import com.example.lydiacontactsapp.data.local.entity.LydiaContactEntity
import com.example.lydiacontactsapp.data.models.Result.Companion.toLydiaContact
import com.example.lydiacontactsapp.data.models.Result.Companion.toLydiaContactEntity
import com.example.lydiacontactsapp.domain.models.LydiaContact
import com.squareup.moshi.Json

data class LydiaResults(
    @Json(name = "info")
    val info: Info,
    @Json(name = "results")
    val results: List<Result>
) {
    companion object {
        fun LydiaResults.toPageContactsPair(): kotlin.Pair<Int, List<LydiaContact>> {
            val list = results.map { it.toLydiaContact() }
            return kotlin.Pair(info.page, list)
        }

        fun LydiaResults.toLydiaContactsEntity(): List<LydiaContactEntity> {
            return results.map { it.toLydiaContactEntity() }
        }
    }
}