package com.example.proyectopokdex.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectopokdex.MainScreen
import com.example.proyectopokdex.PokemonViewModel
import com.example.proyectopokdex.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val pokemonViewModel: PokemonViewModel = viewModel() // Instancia del ViewModel

    NavHost(
        navController = navController,
        startDestination = AppScreens.SplashScreen.route
    ) {
        composable(AppScreens.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(AppScreens.MainScreen.route) {
            MainScreen(navController, pokemonViewModel) // Pasamos el ViewModel
        }
    }
}
