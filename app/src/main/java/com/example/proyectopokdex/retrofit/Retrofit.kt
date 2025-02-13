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
import retrofit2.http.Path

interface PokeApiService {
    @GET("pokemon?limit=1304")
    suspend fun getAllPokemon(): PokemonResponse
    @GET("pokemon/{name}/")
    suspend fun getPokemonDetail(@Path("name") name: String): PokemonDetailResponse
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
    private val _pokemonList = mutableStateOf<List<MyPoke>>(emptyList())
    val pokemonList: State<List<MyPoke>> = _pokemonList

    private val _selectedPokemon = mutableStateOf<MyPoke?>(null)
    val selectedPokemon: State<MyPoke?> = _selectedPokemon

    init {
        fetchPokemon()
    }

    private fun fetchPokemon() {
        viewModelScope.launch {
            try {
                val pokemonResponse = RetrofitInstance.api.getAllPokemon()
                val pokemonWithBasicData = pokemonResponse.results.map { pokemon ->
                    MyPoke(
                        name = pokemon.name.replaceFirstChar(Char::uppercase),
                        type = "Desconocido",
                        id = pokemon.getId(),
                        imageUrl = pokemon.getImageUrl(),
                        ability = "Desconocido"
                    )
                }
                _pokemonList.value = pokemonWithBasicData
            } catch (e: Exception) {
                println("Error al obtener la lista de Pokémon: ${e.message}")
            }
        }
    }

    fun setSelectedPokemon(pokemon: MyPoke) {
        // Mostrar un estado temporal mientras se cargan los datos
        _selectedPokemon.value = pokemon.copy(
            type = "Cargando...",
            ability = "Cargando..."
        )

        viewModelScope.launch {
            try {
                // Obtener detalles del Pokémon directamente
                val pokemonDetail = RetrofitInstance.api.getPokemonDetail(pokemon.name.lowercase())

                // Extraer tipos
                val types = pokemonDetail.types.map { it.type.name.replaceFirstChar(Char::uppercase) }

                // Extraer habilidades
                val abilities = pokemonDetail.abilities.map { it.ability.name.replaceFirstChar(Char::uppercase) }

                // Actualizar el Pokémon seleccionado con datos reales
                _selectedPokemon.value = pokemon.copy(
                    type = types.joinToString(", "),
                    ability = abilities.joinToString(", ")
                )
            } catch (e: Exception) {
                println("Error al obtener detalles de ${pokemon.name}: ${e.message}")

                // Si hay un error, mantener el Pokémon sin modificar pero mostrar un mensaje
                _selectedPokemon.value = pokemon.copy(
                    type = "Error al cargar",
                    ability = "Error al cargar"
                )
            }
        }
    }
}