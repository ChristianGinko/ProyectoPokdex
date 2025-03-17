package com.example.proyectopokdex.retrofit

import com.google.gson.annotations.SerializedName

// Respuesta de la API cuando obtenemos la lista de Pokémon de la Generación 1
data class GenerationResponse(
    val id: Int,
    val name: String,
    @SerializedName("pokemon_species") val pokemonSpecies: List<PokemonSpecies>
)

// Representación de un Pokémon en la lista de la Generación 1
data class PokemonSpecies(
    val name: String,
    val url: String
)

data class TypeResponse(
    val id: Int,
    val name: String,
    @SerializedName("pokeTypes") val PokeTypes: List<PokeTypes>
)

data class PokeTypes(
    val name: String,
    val url: String
)

// Respuesta cuando pedimos detalles de un Pokémon específico
data class PokemonDetailResponse(
    val id: Int,
    val name: String,
    val sprites: PokemonSprites,
    val types: List<TypeSlot>,
    val abilities: List<AbilitySlot>,
)

// Para extraer la imagen del Pokémon
data class PokemonSprites(
    @SerializedName("front_default") val imageUrl: String
)

// Lista de tipos del Pokémon
data class TypeSlot(
    val type: PokemonType
)

// Lista de habilidades del Pokémon
data class AbilitySlot(
    val ability: Ability
)

// Representación de un tipo de Pokémon
data class PokemonType(
    val name: String,
    val url: String
)

// Representación de una habilidad de Pokémon
data class Ability(
    val name: String,
    val url: String
)

// ✅ Corrección: La API ya devuelve una lista de LocationAreaEncounter directamente
data class LocationAreaEncounter(
    @SerializedName("location_area") val locationArea: NamedAPIResource
)

data class NamedAPIResource(
    @SerializedName("name") val name: String
)

// Función para obtener el ID del Pokémon desde su URL
fun PokemonSpecies.getId(): String {
    return try {
        url.split("/").filter { it.isNotEmpty() }.last()
    } catch (e: Exception) {
        "0" // Devuelve "0" si hay un error
    }
}

// Función para obtener la imagen del Pokémon desde su ID
fun PokemonSpecies.getImageUrl(): String {
    val id = this.getId()
    return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
}