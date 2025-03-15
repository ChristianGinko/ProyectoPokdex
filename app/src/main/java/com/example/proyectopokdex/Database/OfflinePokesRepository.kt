package com.example.proyectopokdex.Database

import com.example.proyectopokdex.entities.MyPoke
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class OfflinePokesRepository(private val pokemonDao: PokemonDao) : PokesRepository {
    override fun getAllPokesStream(): Flow<List<MyPoke>> = pokemonDao.getAllPokes()

    override fun getAllPokesGenerationStream(id: String): Flow<List<MyPoke>> {

        return try {
            val pokes = pokemonDao.getAllPokesByGeneration(id)
            pokes

        }catch (e:Exception){
            println("Error al obtener desde la DB ${e.message}")
            flowOf<List<MyPoke>>()
        }

    }

    override fun getPokeStream(id: String): Flow<MyPoke?> = pokemonDao.getPokemon(id)

    override suspend fun insertPoke(poke: MyPoke) = pokemonDao.insertPokemon(poke)

    override suspend fun insertAllPoke(pokes: List<MyPoke>) = pokemonDao.insertAllPokemon(pokes)

    override suspend fun deletePoke(poke: MyPoke) = pokemonDao.deletePokemon(poke)

    override suspend fun updatePoke(poke: MyPoke) = pokemonDao.updatePokemon(poke)

}