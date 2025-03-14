package com.example.proyectopokdex.Database

import com.example.proyectopokdex.MyPoke
import kotlinx.coroutines.flow.Flow

interface PokesRepository {

    fun getAllPokesStream(): Flow<List<MyPoke>>

    fun getPokeStream(id: String): Flow<MyPoke?>

    suspend fun insertPoke(poke: MyPoke)

    suspend fun deletePoke(poke: MyPoke)

    suspend fun updatePoke(poke: MyPoke)

}