package com.example.lydiacontactsapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lydiacontactsapp.domain.models.LydiaContact

@Entity(tableName = "contacts")
data class LydiaContactEntity(
    @PrimaryKey val id :String,
    val title : String,
    val firstName :String,
    val lastName :String,
    val imageUrl : String,
    val phone :String,
    val city : String
){
    companion object{
        fun LydiaContactEntity.toLydiaContact() = LydiaContact(
            this.firstName,
            this.lastName,
            this.phone
        )
    }
}