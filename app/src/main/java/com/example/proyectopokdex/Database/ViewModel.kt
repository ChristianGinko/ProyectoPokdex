package com.example.proyectopokdex.Database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyectopokdex.retrofit.PokemonViewModel

class PokemonViewModelFactory(private val pokesRepository: PokesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PokemonViewModel(pokesRepository) as T
        }
        throw IllegalArgumentException("Clase ViewModel desconocida")
    }
}