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
    @GET("pokemon?limit=1304")
    suspend fun getAllPokemon(): PokemonResponse
    @GET("type?limit=21/")
    suspend fun getTypes(): TypeResponse
    @GET("type/{name}/")
    suspend fun getTypeDetail(@retrofit2.http.Path("name") name: String): TypeDetailResponse
    @GET ("ability?limit=367/")
    suspend fun getAbilities(): AbilityResponse
    @GET ("ability/{name}/")
    suspend fun getAbilityDetail(@retrofit2.http.Path("name") name: String): AbilityDetailResponse
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
                val pokemonResponse = RetrofitInstance.api.getAllPokemon()
                val typeResponse = RetrofitInstance.api.getTypes()
                val abilityResponse = RetrofitInstance.api.getAbilities()

                // Crear un mapa para almacenar una lista de tipos por Pokémon
                val pokemonTypeMap = mutableMapOf<String, MutableList<String>>()

                typeResponse.results.forEach { type ->
                    val typeDetail = RetrofitInstance.api.getTypeDetail(type.name) // Obtener detalles del tipo
                    typeDetail.pokemon.forEach { pokemonEntry ->
                        val pokemonName = pokemonEntry.pokemon.name
                        if (!pokemonTypeMap.containsKey(pokemonName)) {
                            pokemonTypeMap[pokemonName] = mutableListOf()
                        }
                        pokemonTypeMap[pokemonName]?.add(type.name) // Agregar el tipo a la lista del Pokémon
                    }
                }

                val pokemonAbilityMap = mutableMapOf<String, MutableList<String>>()

                abilityResponse.results.forEach { ability ->
                    val abilityDetail = RetrofitInstance.api.getAbilityDetail(ability.name) // Obtener detalles del tipo
                    abilityDetail.pokemon.forEach { pokemonEntry ->
                        val pokemonName = pokemonEntry.pokemon.name
                        if (!pokemonAbilityMap.containsKey(pokemonName)) {
                            pokemonAbilityMap[pokemonName] = mutableListOf()
                        }
                        pokemonAbilityMap[pokemonName]?.add(ability.name) // Agregar el tipo a la lista del Pokémon
                    }
                }

                val pokemonWithTypesAndAbilities = pokemonResponse.results.map { pokemon ->
                    MyPoke(
                        name = pokemon.name.capitalize(),
                        type = pokemonTypeMap[pokemon.name]?.joinToString(", ") { it.capitalize() } ?: "Desconocido", // Juntar todos los tipos
                        id = pokemon.getId(),
                        imageUrl = pokemon.getImageUrl(),
                        ability = pokemonAbilityMap[pokemon.name]?.joinToString(", ") { it.capitalize() } ?: "Desconocido"
                    )
                }

                _pokemonList.value = pokemonWithTypesAndAbilities
            } catch (e: Exception) {
                println("Error al obtener Pokémon: ${e.message}")
            }
        }
    }

    fun setSelectedPokemon(pokemon: MyPoke) {
        _selectedPokemon.value = pokemon
    }
}