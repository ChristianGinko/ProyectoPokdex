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

fun Pokemon.getId(): String {
    return url.split("/".toRegex()).dropLast(1).last()
}