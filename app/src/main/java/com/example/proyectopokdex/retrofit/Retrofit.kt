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
    suspend fun getTypeById(@Path("id") id: String): TypeResponse
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

class PokemonViewModel (private val pokesRepository: PokesRepository) : ViewModel() {
    private val _pokemonList = mutableStateOf<List<MyPoke>>(emptyList())
    val pokemonList: State<List<MyPoke>> = _pokemonList

    private val _selectedPokemon = mutableStateOf<MyPoke?>(null)
    val selectedPokemon: State<MyPoke?> = _selectedPokemon

    private var generationId: String = "1" // Por defecto, usamos la generación 1 (Kanto)

    init {
//        fetchPokemon()
    }

    // Función para cambiar la generación seleccionada
    fun setGeneration(generationId: String) {
        this.generationId = generationId
        fetchPokemon() // Llamar nuevamente a la API con la nueva generación
    }

    private fun fetchPokemon() {
        viewModelScope.launch {
            try {
                val generationPokesDb = pokesRepository.getAllPokesGenerationStream(generationId)

                generationPokesDb.collect { pokes ->
                    if (pokes.isEmpty()) {
                        // Usamos el ID de generación seleccionado dinámicamente
                        val generationResponse = withContext(Dispatchers.IO) {
                            RetrofitInstance.api.getGenerationById(generationId)
                        }
                        // Mapear los Pokémon a la estructura MyPoke
                        val pokemonWithBasicData = generationResponse.pokemonSpecies.map { pokemon ->
                            MyPoke(
                                name = pokemon.name.replaceFirstChar(Char::uppercase),
                                type = emptyList(), // ✅ Ahora es una lista vacía
                                id = pokemon.getId().toInt(),
                                imageUrl = pokemon.getImageUrl(),
                                ability = "Desconocido",
                                encounter = "Desconocido",
                                generationId = generationId
                            )
                        }

                        // Ordenar los Pokémon por ID (suponiendo que el ID se obtiene correctamente)
                        val sortedPokemonList =
                            pokemonWithBasicData.sortedBy { it.id.toInt() } // Ordena por ID numérico

                        pokesRepository.insertAllPoke(sortedPokemonList)
//                        for(poke in sortedPokemonList){
//                            pokesRepository.insertPoke(poke)
//                        }

                        _pokemonList.value = sortedPokemonList
                    } else {
                        _pokemonList.value = pokes
                    }
                }

            } catch (e: Exception) {
                println("Error al obtener la lista de Pokémon de la generación ${generationId}: ${e.message}")
            }
        }
    }

    fun setSelectedPokemon(pokemon: MyPoke) {
        _selectedPokemon.value = pokemon.copy(
            type = emptyList(), // ✅ Inicializamos con una lista vacía
            ability = "Cargando...",
            encounter = "Cargando..."
        )

        viewModelScope.launch {
            try {
                val pokemonDetail = RetrofitInstance.api.getPokemonDetail(pokemon.name.lowercase())

                // Obtener la lista de tipos desde la API
                val typeList = pokemonDetail.types.map { typeInfo ->
                    withContext(Dispatchers.IO) {
                        val typeResponse = RetrofitInstance.api.getTypeById(typeInfo.type.name.lowercase())
                        MyType(id = typeResponse.id, name = typeResponse.name.replaceFirstChar(Char::uppercase))
                    }
                }

                val abilities = pokemonDetail.abilities.map { it.ability.name.replaceFirstChar(Char::uppercase) }

                val pokemonEncounters = RetrofitInstance.api.getPokemonEncounters(pokemon.id.toString())
                val encounters = if (pokemonEncounters.isNotEmpty()) {
                    pokemonEncounters.joinToString("\n") { it.locationArea.name.replaceFirstChar(Char::uppercase) }
                } else {
                    "Sin encuentros disponibles"
                }

                _selectedPokemon.value = pokemon.copy(
                    type = typeList, // ✅ Se guarda la lista completa en Room
                    ability = abilities.joinToString(", "),
                    encounter = encounters
                )
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
