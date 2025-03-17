package com.example.proyectopokdex.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MyType (
    @PrimaryKey val id: Int,
    val name: String
)