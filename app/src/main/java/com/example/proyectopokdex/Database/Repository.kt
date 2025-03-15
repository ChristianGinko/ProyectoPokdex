package com.example.proyectopokdex.Database

import com.example.proyectopokdex.entities.MyPoke
import kotlinx.coroutines.flow.Flow

interface PokesRepository {

    fun getAllPokesStream(): Flow<List<MyPoke>>

    fun getAllPokesGenerationStream(id: String): Flow<List<MyPoke>>

    fun getPokeStream(id: String): Flow<MyPoke?>

    suspend fun insertPoke(poke: MyPoke)

    suspend fun insertAllPoke(pokes: List<MyPoke>)

    suspend fun deletePoke(poke: MyPoke)

    suspend fun updatePoke(poke: MyPoke)

}