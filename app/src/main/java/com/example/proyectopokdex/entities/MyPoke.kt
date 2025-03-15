package com.example.proyectopokdex.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MyPoke(
    @PrimaryKey val id: Int,
    val name: String,
    val type: String,
    val imageUrl: String,
    val ability: String,
    val encounter: String,
    val generationId: String
)