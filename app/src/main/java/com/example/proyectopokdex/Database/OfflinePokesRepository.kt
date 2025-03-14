package com.example.proyectopokdex.Database

import com.example.proyectopokdex.MyPoke
import kotlinx.coroutines.flow.Flow

class OfflinePokesRepository(private val pokemonDao: PokemonDao) : PokesRepository {
    override fun getAllPokesStream(): Flow<List<MyPoke>> = pokemonDao.getAllPokes()

    override fun getPokeStream(id: String): Flow<MyPoke?> = pokemonDao.getPokemon(id)

    override suspend fun insertPoke(poke: MyPoke) = pokemonDao.insertPokemon(poke)

    override suspend fun deletePoke(poke: MyPoke) = pokemonDao.deletePokemon(poke)

    override suspend fun updatePoke(poke: MyPoke) = pokemonDao.updatePokemon(poke)

}