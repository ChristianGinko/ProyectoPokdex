package com.example.proyectopokdex.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.proyectopokdex.entities.MyPoke
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPokemon(pokemon: MyPoke)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPokemon(pokemons: List<MyPoke>)

    @Update
    suspend fun updatePokemon(pokemon: MyPoke)

    @Delete
    suspend fun deletePokemon(pokemon: MyPoke)

    @Query("SELECT * FROM MyPoke WHERE id = :id")
    fun getPokemon(id: String): Flow<MyPoke>

    @Query("SELECT * FROM MyPoke ORDER BY name ASC")
    fun getAllPokes(): Flow<List<MyPoke>>

    @Query("SELECT * FROM MyPoke WHERE generationId = :id ORDER BY id ASC")
    fun getAllPokesByGeneration(id: String): Flow<List<MyPoke>>

    @Query("DELETE FROM MyPoke WHERE generationId = :id")
    fun deleteAllForGeneration(id: String)
}