package com.example.proyectopokdex.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity
data class MyPoke(
    @PrimaryKey val id: Int,
    val name: String,
    val type: List<MyType>,
    val imageUrl: String,
    val ability: String,
    val encounter: String,
    val generationId: String
)

@Entity
data class MyType (
    @PrimaryKey val id: Int,
    val name: String
)

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromTypeList(types: List<MyType>): String {
        return gson.toJson(types) // Convertimos la lista a JSON
    }

    @TypeConverter
    fun toTypeList(typesString: String): List<MyType> {
        val typeListType = object : TypeToken<List<MyType>>() {}.type
        return gson.fromJson(typesString, typeListType) ?: emptyList() // Convertimos JSON a lista
    }
}