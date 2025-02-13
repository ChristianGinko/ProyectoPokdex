package com.example.proyectopokdex.retrofit

import com.google.gson.annotations.SerializedName

// Respuesta de la API cuando obtenemos la lista de Pokémon
data class PokemonResponse(
    val count: Int,
    val results: List<Pokemon>
)

// Representación básica de un Pokémon en la lista
data class Pokemon(
    val name: String,
    val url: String
)

// Respuesta cuando pedimos detalles de un Pokémon específico
data class PokemonDetailResponse(
    val id: Int,
    val name: String,
    val sprites: PokemonSprites,
    val types: List<TypeSlot>,
    val abilities: List<AbilitySlot>
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

// Función para obtener el ID del Pokémon desde su URL
fun Pokemon.getId(): String {
    return url.split("/").filter { it.isNotEmpty() }.last()
}

// Función para obtener la imagen del Pokémon desde su ID
fun Pokemon.getImageUrl(): String {
    val id = this.getId()
    return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
}