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
fun TypeScreen (navController: NavController, viewModel: PokemonViewModel) {
    Lista(navController, viewModel)
}

@Composable
fun Lista (navController: NavController, viewModel: PokemonViewModel) {
    Box (
        modifier = Modifier
            .fillMaxSize()
            .padding (
                top = 30.dp,
                bottom = 50.dp
            )
    ) {
        Image(
            painter = painterResource(R.drawable.typescreen),
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
                viewModel.setType("1")
                navController.navigate(AppScreens.MainScreen.route)
            }) {
                Text(text = "Normal")
            }
            Button(onClick = {
                viewModel.setType("2")
                navController.navigate(AppScreens.MainScreen.route)
            }) {
                Text(text = "Fighting")
            }
            Button(onClick = {
                viewModel.setType("3")
                navController.navigate(AppScreens.MainScreen.route)
            }) {
                Text(text = "Flying")
            }
            Button(onClick = {
                viewModel.setType("4")
                navController.navigate(AppScreens.MainScreen.route)
            }) {
                Text(text = "Poison")
            }
            Button(onClick = {
                viewModel.setType("5")
                navController.navigate(AppScreens.MainScreen.route)
            }) {
                Text(text = "Ground")
            }
            Button(onClick = {
                viewModel.setType("6")
                navController.navigate(AppScreens.MainScreen.route)
            }) {
                Text(text = "Rock")
            }
            Button(onClick = {
                viewModel.setType("7")
                navController.navigate(AppScreens.MainScreen.route)
            }) {
                Text(text = "Bug")
            }
            Button(onClick = {
                viewModel.setType("8")
                navController.navigate(AppScreens.MainScreen.route)
            }) {
                Text(text = "Ghost")
            }
            Button(onClick = {
                viewModel.setType("9")
                navController.navigate(AppScreens.MainScreen.route)
            }) {
                Text(text = "Steel")
            }
            Button(onClick = {
                viewModel.setType("10")
                navController.navigate(AppScreens.MainScreen.route)
            }) {
                Text(text = "Fire")
            }
            Button(onClick = {
                viewModel.setType("11")
                navController.navigate(AppScreens.MainScreen.route)
            }) {
                Text(text = "Water")
            }
            Button(onClick = {
                viewModel.setType("12")
                navController.navigate(AppScreens.MainScreen.route)
            }) {
                Text(text = "Grass")
            }
            Button(onClick = {
                viewModel.setType("13")
                navController.navigate(AppScreens.MainScreen.route)
            }) {
                Text(text = "Electric")
            }
            Button(onClick = {
                viewModel.setType("14")
                navController.navigate(AppScreens.MainScreen.route)
            }) {
                Text(text = "Psychic")
            }
            Button(onClick = {
                viewModel.setType("15")
                navController.navigate(AppScreens.MainScreen.route)
            }) {
                Text(text = "Ice")
            }
            Button(onClick = {
                viewModel.setType("16")
                navController.navigate(AppScreens.MainScreen.route)
            }) {
                Text(text = "Dragon")
            }
            Button(onClick = {
                viewModel.setType("17")
                navController.navigate(AppScreens.MainScreen.route)
            }) {
                Text(text = "Dark")
            }
            Button(onClick = {
                viewModel.setType("18")
                navController.navigate(AppScreens.MainScreen.route)
            }) {
                Text(text = "Fairy")
            }
            Button(onClick = {
                viewModel.setType("19")
                navController.navigate(AppScreens.MainScreen.route)
            }) {
                Text(text = "Stellar")
            }
            Button(onClick = {
                viewModel.setType("10001")
                navController.navigate(AppScreens.MainScreen.route)
            }) {
                Text(text = "Unknown")
            }
            Button(onClick = {
                viewModel.setType("10002")
                navController.navigate(AppScreens.MainScreen.route)
            }) {
                Text(text = "Shadow")
            }
            Button(onClick = {
                navController.navigate(AppScreens.MainList.route)
            }) {
                Text(text = "Back")
            }
        }
    }
}