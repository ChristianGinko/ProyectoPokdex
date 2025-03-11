package com.example.proyectopokdex.DataBase.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.proyectopokdex.DataBase.Entities.Pokemon
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pokemon: Pokemon)

    @Update
    suspend fun update(pokemon: Pokemon)

    @Query("SELECT * FROM pokemon")
    fun getAllPokemon(): Flow<List<Pokemon>>
}