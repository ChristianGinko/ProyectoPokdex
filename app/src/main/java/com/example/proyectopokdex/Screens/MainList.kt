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
import androidx.compose.foundation.layout.padding

@Composable
fun MainList (navController: NavController){
    Estructura(navController)
}

@Composable
fun Estructura(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding (
                top = 30.dp,
                bottom = 50.dp
            )
    ){
                Image(
                    painter = painterResource(R.drawable.mainlist),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier.fillMaxSize()
                )
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                onClick = {
                navController.navigate(AppScreens.LeagueScreen.route)
            }
            ){
                Text(text="Ligas")
            }
            Button(
                onClick = {
                    navController.navigate(AppScreens.TypeScreen.route)
                }
            ){
                Text(text="Tipos")
            }
        }
            }
}