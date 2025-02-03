package com.example.proyectopokdex.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.State
import com.example.proyectopokdex.retrofit.RetrofitInstance
import com.example.proyectopokdex.retrofit.getId
import com.example.proyectopokdex.MyPoke
import com.example.proyectopokdex.R
import com.example.proyectopokdex.navigation.AppScreens
import com.example.proyectopokdex.retrofit.PokemonViewModel

@Composable
fun MainScreen(navController: NavController, viewModel: PokemonViewModel) {
    val pokes by viewModel.pokemonList
    MyPokes(navController, viewModel, pokes)
}

@Composable
fun MyComponent(
    poke: MyPoke,
    viewModel: PokemonViewModel,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .border(5.dp, Color.Black)
            .fillMaxWidth()
            .clickable {
                viewModel.setSelectedPokemon(poke) // Guardamos el Pokémon seleccionado
                navController.navigate(AppScreens.DataScreen.route) // Navegamos a DataScreen
            }
    ) {
        Image(
            painter = painterResource(R.drawable.pok_ball),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.offset(y = 50.dp)
        ) {
            Text(
                text = "#${poke.id}",
                color = Color(0xFFFFE031),
                style = TextStyle(fontSize = 35.sp),
                modifier = Modifier.offset(x = 50.dp)
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
            style = TextStyle(fontSize = 35.sp),
            modifier = Modifier
                .offset(x = 70.dp)
        )
    }
}

@Composable
fun MyPokes(navController: NavController, viewModel: PokemonViewModel, pokes: List<MyPoke>) {
    LazyColumn(
        modifier = Modifier.padding(top = 30.dp, bottom = 50.dp)
    ) {
        items(pokes) { poke ->
            MyComponent(poke = poke, viewModel = viewModel, navController = navController)
        }
    }
}