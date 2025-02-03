package com.example.proyectopokdex.retrofit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectopokdex.MyPoke
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PokeApiService {
    @GET("pokemon?limit=1304&offset=0")
    suspend fun getAllPokemon(): PokemonResponse
}

object RetrofitInstance {
    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    val api: PokeApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokeApiService::class.java)
    }
}

class PokemonViewModel : ViewModel() {
    val _pokemonList = mutableStateOf<List<MyPoke>>(emptyList())
    val pokemonList: State<List<MyPoke>> = _pokemonList

    private val _selectedPokemon = mutableStateOf<MyPoke?>(null)
    val selectedPokemon: State<MyPoke?> = _selectedPokemon

    init {
        fetchPokemon()
    }

    fun fetchPokemon() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getAllPokemon()
                _pokemonList.value = response.results.map { pokemon ->
                    MyPoke(
                        name = pokemon.name.capitalize(),
                        type = "Desconocido", // Aquí podrías hacer otra petición para obtener el tipo
                        id = pokemon.getId(),
                        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.getId()}.png"
                    )
                }
            } catch (e: Exception) {
                println("Error al obtener Pokémon: ${e.message}")
            }
        }
    }

    fun setSelectedPokemon(pokemon: MyPoke) {
        _selectedPokemon.value = pokemon
    }
}