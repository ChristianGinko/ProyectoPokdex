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
    @GET("pokemon/{id}/encounters")
    suspend fun getPokemonEncounters(@Path("id") id: String): List<LocationAreaEncounter>
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
                        ability = "Desconocido",
                        encounter = "Desconocido"
                    )
                }
                _pokemonList.value = pokemonWithBasicData
            } catch (e: Exception) {
                println("Error al obtener la lista de Pokémon: ${e.message}")
            }
        }
    }

    fun setSelectedPokemon(pokemon: MyPoke) {
        _selectedPokemon.value = pokemon.copy(
            type = "Cargando...",
            ability = "Cargando...",
            encounter = "Cargando..."
        )

        viewModelScope.launch {
            try {
                val pokemonDetail = RetrofitInstance.api.getPokemonDetail(pokemon.name.lowercase())

                val types = pokemonDetail.types.map { it.type.name.replaceFirstChar(Char::uppercase) }
                val abilities = pokemonDetail.abilities.map { it.ability.name.replaceFirstChar(Char::uppercase) }

                val pokemonEncounters = RetrofitInstance.api.getPokemonEncounters(pokemon.id)

                val encounters = if (pokemonEncounters.isNotEmpty()) {
                    pokemonEncounters.joinToString("\n") { it.locationArea.name.replaceFirstChar(Char::uppercase) }
                } else {
                    "Sin encuentros disponibles"
                }

                _selectedPokemon.value = pokemon.copy(
                    type = types.joinToString(", "),
                    ability = abilities.joinToString(", "),
                    encounter = encounters
                )
            } catch (e: Exception) {
                println("Error al obtener detalles de ${pokemon.name}: ${e.message}")

                _selectedPokemon.value = pokemon.copy(
                    type = "Error al cargar",
                    ability = "Error al cargar",
                    encounter = "Error al cargar"
                )
            }
        }
    }
}