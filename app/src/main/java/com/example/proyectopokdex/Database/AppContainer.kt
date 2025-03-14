package com.example.proyectopokdex.Database

import android.content.Context

interface AppContainer {
    val pokesRepository: PokesRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [PokesRepository]
     */
    override val pokesRepository: PokesRepository by lazy {
        OfflinePokesRepository(PokemonDatabase.getDatabase(context).pokemonDao())
    }
}