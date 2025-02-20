package com.example.proyectopokdex.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectopokdex.R
import com.example.proyectopokdex.navigation.AppScreens
import com.example.proyectopokdex.retrofit.PokemonViewModel
import androidx.compose.foundation.layout.padding

@Composable
fun LeagueScreen (navController: NavController, viewModel: PokemonViewModel){
    Structures(navController, viewModel)
}

@Composable
fun Structures(navController: NavController, viewModel: PokemonViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding (
                top = 30.dp,
                bottom = 50.dp
            )
    ) {
        Image(
            painter = painterResource(R.drawable.ligas_pokemon),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(onClick = {
                viewModel.setGeneration("1") // Kanto
                navController.navigate(AppScreens.MainScreen.route)
            }) {
                Text(text = "Kanto")
            }
            Button(onClick = {
                viewModel.setGeneration("2") // Johto
                navController.navigate(AppScreens.MainScreen.route)
            }) {
                Text(text = "Johto")
            }
            Button(onClick = {
                viewModel.setGeneration("3") // Hoenn
                navController.navigate(AppScreens.MainScreen.route)
            }) {
                Text(text = "Hoenn")
            }
            Button(onClick = {
                viewModel.setGeneration("4") // Sinnoh
                navController.navigate(AppScreens.MainScreen.route)
            }) {
                Text(text = "Sinnoh")
            }
            Button(onClick = {
                viewModel.setGeneration("5") // Unova
                navController.navigate(AppScreens.MainScreen.route)
            }) {
                Text(text = "Unova")
            }
            Button(onClick = {
                viewModel.setGeneration("6") // Kalos
                navController.navigate(AppScreens.MainScreen.route)
            }) {
                Text(text = "Kalos")
            }
            Button(onClick = {
                viewModel.setGeneration("7") // Alola
                navController.navigate(AppScreens.MainScreen.route)
            }) {
                Text(text = "Alola")
            }
            Button(onClick = {
                viewModel.setGeneration("8") // Galar
                navController.navigate(AppScreens.MainScreen.route)
            }) {
                Text(text = "Galar")
            }
            Button(onClick = {
                viewModel.setGeneration("9") // Paldea
                navController.navigate(AppScreens.MainScreen.route)
            }) {
                Text(text = "Paldea")
            }
        }
    }
}