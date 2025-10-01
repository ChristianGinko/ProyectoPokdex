package com.example.proyectopokdex.retrofit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectopokdex.Database.PokesRepository
import com.example.proyectopokdex.entities.MyPoke
import com.example.proyectopokdex.entities.MyType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiService {
    @GET("generation/{id}")
    suspend fun getGenerationById(@Path("id") generationId: String): GenerationResponse
    @GET("pokemon/{name}/")
    suspend fun getPokemonDetail(@Path("name") name: String): PokemonDetailResponse
    @GET("pokemon/{id}/encounters")
    suspend fun getPokemonEncounters(@Path("id") id: String): List<LocationAreaEncounter>
    @GET("type/{id}/")
    suspend fun getTypeById(@Path("id") typeId: String): TypeResponse
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

class PokemonViewModel(private val pokesRepository: PokesRepository) : ViewModel() {
    private val _pokemonList = mutableStateOf<List<MyPoke>>(emptyList())
    val pokemonList: State<List<MyPoke>> = _pokemonList

    private val _selectedPokemon = mutableStateOf<MyPoke?>(null)
    val selectedPokemon: State<MyPoke?> = _selectedPokemon

    private var generationId: String = "1" // Por defecto, generación 1 (Kanto)
    private var typeId: String = "1"

    fun setGeneration(generationId: String) {
        this.generationId = generationId
        fetchPokemon()
    }

    fun setType(typeId: String) {
        this.typeId = typeId
        fetchPokemon()
    }

    private fun fetchPokemon() {
        viewModelScope.launch {
            try {
                val generationPokesDb = pokesRepository.getAllPokesGenerationStream(generationId)

                generationPokesDb.collect { pokes ->
                    if (pokes.isEmpty()) {
                        val generationResponse = withContext(Dispatchers.IO) {
                            RetrofitInstance.api.getGenerationById(generationId)
                        }

                        val pokemonWithBasicData = generationResponse.pokemonSpecies.map { pokemon ->
                            MyPoke(
                                name = pokemon.name.replaceFirstChar(Char::uppercase),
                                type = emptyList(),
                                id = pokemon.getId().toInt(),
                                imageUrl = pokemon.getImageUrl(), // ✅ Ya incluye la URL de la imagen
                                ability = "Desconocido",
                                encounter = "Desconocido",
                                generationId = generationId
                            )
                        }

                        val sortedPokemonList = pokemonWithBasicData.sortedBy { it.id }

                        pokesRepository.insertAllPoke(sortedPokemonList)
                        _pokemonList.value = sortedPokemonList
                    } else {
                        _pokemonList.value = pokes
                    }
                }
            } catch (e: Exception) {
                println("Error al obtener la lista de Pokémon: ${e.message}")
            }
        }
    }

    fun setSelectedPokemon(pokemon: MyPoke) {
        _selectedPokemon.value = pokemon.copy(
            type = emptyList(),
            ability = "Cargando...",
            encounter = "Cargando..."
        )

        viewModelScope.launch {
            try {
                pokesRepository.getPokeStream(pokemon.id.toString()).collect { pokemonFromDb ->
                    if (pokemonFromDb != null && pokemonFromDb.type.isNotEmpty()) {
                        _selectedPokemon.value = pokemonFromDb
                    } else {
                        val pokemonDetail = withContext(Dispatchers.IO) {
                            RetrofitInstance.api.getPokemonDetail(pokemon.name.lowercase())
                        }

                        val typeList = pokemonDetail.types.map { typeInfo ->
                            withContext(Dispatchers.IO) {
                                val typeResponse = RetrofitInstance.api.getTypeById(typeInfo.type.name.lowercase())
                                MyType(id = typeResponse.id, name = typeResponse.name.replaceFirstChar(Char::uppercase))
                            }
                        }

                        val abilities = pokemonDetail.abilities.map { it.ability.name.replaceFirstChar(Char::uppercase) }

                        val pokemonEncounters = withContext(Dispatchers.IO) {
                            RetrofitInstance.api.getPokemonEncounters(pokemon.id.toString())
                        }
                        val encounters = if (pokemonEncounters.isNotEmpty()) {
                            pokemonEncounters.joinToString("\n") { it.locationArea.name.replaceFirstChar(Char::uppercase) }
                        } else {
                            "Sin encuentros disponibles"
                        }

                        val updatedPokemon = pokemon.copy(
                            type = typeList,
                            ability = abilities.joinToString(", "),
                            encounter = encounters
                        )

                        pokesRepository.updatePoke(updatedPokemon)
                        _selectedPokemon.value = updatedPokemon
                    }
                }
            } catch (e: Exception) {
                println("Error al obtener detalles de ${pokemon.name}: ${e.message}")
                _selectedPokemon.value = pokemon.copy(
                    type = emptyList(),
                    ability = "Error al cargar",
                    encounter = "Error al cargar"
                )
            }
        }
    }
}