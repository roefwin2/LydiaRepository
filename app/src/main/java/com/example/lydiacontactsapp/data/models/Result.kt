package com.example.lydiacontactsapp.data.models


import com.example.lydiacontactsapp.data.local.entity.LydiaContactEntity
import com.example.lydiacontactsapp.domain.models.LydiaContact
import com.squareup.moshi.Json

data class Result(
    @Json(name = "cell")
    val cell: String,
    @Json(name = "dob")
    val dob: Dob,
    @Json(name = "email")
    val email: String,
    @Json(name = "gender")
    val gender: String,
    @Json(name = "id")
    val id: Id,
    @Json(name = "location")
    val location: Location,
    @Json(name = "login")
    val login: Login,
    @Json(name = "name")
    val name: Name,
    @Json(name = "nat")
    val nat: String,
    @Json(name = "phone")
    val phone: String,
    @Json(name = "picture")
    val picture: Picture,
    @Json(name = "registered")
    val registered: Registered
){
    companion object{
        fun Result.toLydiaContact() = LydiaContact(
            name.first,
            name.last,
            phone
        )

        fun Result.toLydiaContactEntity() = LydiaContactEntity(
            firstName = name.first,
            lastName = name.last,
            phone = phone,
            imageUrl = picture.thumbnail,
            title = name.title,
            city = location.city
        )
    }
}