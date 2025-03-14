package com.example.proyectopokdex.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.proyectopokdex.MyPoke
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPokemon(pokemon: MyPoke)

    @Update
    suspend fun updatePokemon(pokemon: MyPoke)

    @Delete
    suspend fun deletePokemon(pokemon: MyPoke)

    @Query("SELECT * FROM MyPoke WHERE id = :id")
    fun getPokemon(id: String): Flow<MyPoke>

    @Query("SELECT * FROM MyPoke ORDER BY name ASC")
    fun getAllPokes(): Flow<List<MyPoke>>
}