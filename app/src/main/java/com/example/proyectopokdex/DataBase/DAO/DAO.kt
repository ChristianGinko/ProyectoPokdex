package com.example.proyectopokdex.DataBase.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.proyectopokdex.DataBase.Entities.Pokemon

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemon")
    fun getAll(): List<Pokemon>

    @Insert
    fun insertAll(vararg pokemon: Pokemon)
}