package com.example.proyectopokdex.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectopokdex.MyPoke
import com.example.proyectopokdex.R
import com.example.proyectopokdex.navigation.AppScreens
import com.example.proyectopokdex.retrofit.PokemonViewModel

@Composable
fun EncountersScreen (navController: NavController, viewModel: PokemonViewModel){
    Encounters(navController, viewModel)
}

@Composable
fun Encounters(navController: NavController, viewModel: PokemonViewModel) {
    val selectedPoke by viewModel.selectedPokemon

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { navController.navigate(AppScreens.DataScreen.route) }
            .padding (
                top = 30.dp,
                bottom = 50.dp
            )
    ) {
        Image(
            painter = painterResource(R.drawable.bosque_fondo),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxSize()
        )
        val scrollState = rememberScrollState()
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            // Mostrar solo el Pokémon seleccionado
            selectedPoke?.let { poke ->
                Place(
                    poke = poke,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun Place(poke: MyPoke, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ){
        Box() {
            Text(
                text = "Places: \n${poke.encounter}",
                style = TextStyle(
                    fontSize = 25.sp,
                    drawStyle = Stroke(width = 15f)
                ),
                color = Color.Black
            )
            Text(
                text = "Places:\n${poke.encounter}",
                style = TextStyle(fontSize = 25.sp),
                color = Color.White
            )
        }
    }
}

/*
@Preview (showBackground = true)
@Composable
fun EncountersPreview(){
    Encounters(navController: NavController, poke: MyPoke)
}
*/