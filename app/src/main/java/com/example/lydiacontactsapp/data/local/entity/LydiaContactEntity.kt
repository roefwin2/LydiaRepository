package com.example.lydiacontactsapp.data.local.entity

import androidx.room.Entity
import com.example.lydiacontactsapp.domain.models.LydiaContact

@Entity(tableName = "contacts")
data class LydiaContactEntity(
    val id : String,
    val label :String,
    val firstName :String,
    val lastName :String,
    val phone :String,
){
    companion object{
        fun LydiaContactEntity.toLydiaContact() = LydiaContact(
            this.firstName,
            this.lastName,
            this.phone
        )
    }
}