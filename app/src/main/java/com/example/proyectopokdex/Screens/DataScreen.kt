package com.example.proyectopokdex.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.proyectopokdex.MyPoke
import com.example.proyectopokdex.R
import com.example.proyectopokdex.navigation.AppScreens
import com.example.proyectopokdex.retrofit.PokemonViewModel
import com.example.proyectopokdex.retrofit.RetrofitInstance
import com.example.proyectopokdex.retrofit.getId
import kotlinx.coroutines.launch

@Composable
fun DataScreen (navController: NavController, viewModel: PokemonViewModel){
    val pokes by viewModel.pokemonList
    Structure(navController, viewModel)
}

@Composable
fun Structure(navController: NavController, viewModel: PokemonViewModel) {
    val selectedPoke by viewModel.selectedPokemon

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { navController.navigate(AppScreens.MainScreen.route) }
            .padding (
                top = 30.dp,
                bottom = 50.dp
            )
    ) {
        Image(
            painter = painterResource(R.drawable.pokedex_stats),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Mostrar solo el Pokémon seleccionado
            selectedPoke?.let { poke ->
                Stats(poke = poke)
            }
            Button(
                onClick = { navController.navigate(AppScreens.MainScreen.route) },
                modifier = Modifier
                    .offset(y = (-200).dp)
            ){
                Text(text = "Back")
            }
        }
    }
}


@Composable
fun Stats(poke: MyPoke) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .offset(y = (-100).dp)
        ) {
            Column(
                modifier = Modifier
                    .offset(x = (-30).dp)
            ) {
                Text(
                    text = "Name: ${poke.name}",
                    style = TextStyle(fontSize = 25.sp)
                )
                Text(
                    text = "Type: ${poke.type}",
                    style = TextStyle(fontSize = 18.sp)
                )
            }
            AsyncImage(
                model = poke.imageUrl,
                contentDescription = "Imagen de ${poke.name}"
            )
        }
        Row(){
            Text(
                text = "Abilities: ${poke.ability}",
                style = TextStyle(fontSize = 18.sp)
            )
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun DataScreenPreview(){
    DataScreen(navController: NavController, poke: MyPoke)
}
*/