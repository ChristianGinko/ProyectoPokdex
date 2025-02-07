package com.example.proyectopokdex.retrofit

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    val count: Int,
    val results: List<Pokemon>
)

data class Pokemon(
    val name: String,
    val url: String
)

data class TypeResponse(
    val count: Int,
    val results: List<PokemonType>
)

data class PokemonType(
    val name: String,
    val url: String
)

data class TypeDetailResponse(
    val pokemon: List<PokemonTypeEntry>
)

data class PokemonTypeEntry(
    val pokemon: Pokemon
)

data class AbilityResponse(
    val count: Int,
    val results: List<Ability>
)

data class Ability(
    val name: String,
    val url: String
)

data class AbilityDetailResponse(
    val pokemon: List<AbilityEntry>
)

data class AbilityEntry(
    val pokemon: Pokemon
)

fun Pokemon.getId(): String {
    return url.split("/").filter { it.isNotEmpty() }.last()
}

fun Pokemon.getImageUrl(): String {
    val id = this.getId()
    return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
}

fun PokemonType.getId(): String {
    return url.split("/").filter { it.isNotEmpty() }.last()
}