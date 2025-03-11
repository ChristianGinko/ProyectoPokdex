package com.example.proyectopokdex.DataBase.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pokemon (
    @PrimaryKey val id: String,
    val name: String,
    val type: String,
    val ability: String,
    val imageUrl: String,
    val encounter: String
)