package com.example.proyectopokdex.Database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyectopokdex.retrofit.PokemonViewModel

class PokemonViewModelFactory(
    private val pokesRepository: PokesRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PokemonViewModel::class.java)) {
            PokemonViewModel(pokesRepository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}