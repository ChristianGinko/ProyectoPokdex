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
import androidx.navigation.NavController
import com.example.proyectopokdex.R
import com.example.proyectopokdex.navigation.AppScreens
import com.example.proyectopokdex.retrofit.PokemonViewModel

@Composable
fun LeagueScreen (navController: NavController, viewModel: PokemonViewModel){
    Structures(navController, viewModel)
}

@Composable
fun Structures (navController: NavController, viewModel: PokemonViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Image(
            painter = painterResource(R.drawable.ligas_pokemon),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxSize()
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ){
            Button(onClick = {
                navController.navigate(AppScreens.MainScreen.route)
            },
            ){
                Text(text = "Kanto")
            }
            Button(onClick = {
                navController.navigate(AppScreens.MainScreen.route)
            },
            ){
                Text(text = "Johto")
            }
            Button(onClick = {
                navController.navigate(AppScreens.MainScreen.route)
            },
            ){
                Text(text = "Hoenn")
            }
            Button(onClick = {
                navController.navigate(AppScreens.MainScreen.route)
            },
            ){
                Text(text = "Sinnoh")
            }
            Button(onClick = {
                navController.navigate(AppScreens.MainScreen.route)
            },
            ){
                Text(text = "Unova")
            }
            Button(onClick = {
                navController.navigate(AppScreens.MainScreen.route)
            },
            ){
                Text(text = "Kalos")
            }
            Button(onClick = {
                navController.navigate(AppScreens.MainScreen.route)
            },
            ){
                Text(text = "Alola")
            }
            Button(onClick = {
                navController.navigate(AppScreens.MainScreen.route)
            },
            ){
                Text(text = "Galar")
            }
            Button(onClick = {
                navController.navigate(AppScreens.MainScreen.route)
            },
            ){
                Text(text = "Hisui")
            }
            Button(onClick = {
                navController.navigate(AppScreens.MainScreen.route)
            },
            ){
                Text(text = "Paldea")
            }
        }
    }
}