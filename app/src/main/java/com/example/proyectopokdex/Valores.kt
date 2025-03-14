package com.example.proyectopokdex

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MyPoke(
    @PrimaryKey val id: String,
    val name: String,
    val type: String,
    val imageUrl: String,
    val ability: String,
    val encounter: String
)