package com.example.proyectopokdex

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.proyectopokdex.navigation.AppScreens
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.example.proyectopokdex.retrofit.RetrofitInstance
import com.example.proyectopokdex.retrofit.getId
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun MainScreen(navController: NavController, viewModel: PokemonViewModel) {
    val pokes by viewModel.pokemonList
    MyPokes(navController, pokes)
}

@Composable
fun MyComponent(poke: MyPoke) {
    Box(
        modifier = Modifier
            .border(5.dp, Color.Black)
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(R.drawable.pok_ball),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Row() {
            Image(
                painter = rememberAsyncImagePainter(poke.imageUrl),
                contentDescription = poke.name,
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Fit
            )
            MyText(poke)
        }
    }
}

@Composable
fun MyText(poke: MyPoke) {
    Column(modifier = Modifier.padding(10.dp)) {
        Text(
            text = poke.name,
            color = Color(0xFFFFE031),
            style = TextStyle(fontSize = 24.sp)
        )
    }
}

@Composable
fun MyPokes(navController: NavController, pokes: List<MyPoke>) {
    LazyColumn(
        modifier = Modifier
            .padding(
                top = 30.dp,
                bottom = 50.dp
            )
    ) {
        items(pokes) { poke ->
            MyComponent(
                poke = poke
            )
        }
    }
}

class PokemonViewModel : ViewModel() {
    private val _pokemonList = mutableStateOf<List<MyPoke>>(emptyList())
    val pokemonList: State<List<MyPoke>> = _pokemonList

    init {
        fetchPokemon()
    }

    private fun fetchPokemon() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getAllPokemon()
                _pokemonList.value = response.results.map { pokemon ->
                    MyPoke(
                        name = pokemon.name.capitalize(),
                        type = "Desconocido", // Aquí podrías hacer otra petición para obtener el tipo
                        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.getId()}.png"
                    )
                }
            } catch (e: Exception) {
                println("Error al obtener Pokémon: ${e.message}")
            }
        }
    }
}