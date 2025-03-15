package com.example.proyectopokdex.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectopokdex.Database.OfflinePokesRepository
import com.example.proyectopokdex.Database.PokemonDatabase
import com.example.proyectopokdex.Database.PokemonViewModelFactory
import com.example.proyectopokdex.MyPoke
import com.example.proyectopokdex.Screens.DataScreen
import com.example.proyectopokdex.Screens.MainScreen
import com.example.proyectopokdex.Screens.SplashScreen
import com.example.proyectopokdex.Screens.EncountersScreen
import com.example.proyectopokdex.Screens.LeagueScreen
import com.example.proyectopokdex.retrofit.PokemonViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val database = PokemonDatabase.getDatabase(context) // Obtiene la base de datos
    val pokesRepository = OfflinePokesRepository(database.pokemonDao()) // Usa el DAO

    // Ahora sí puedes usar pokesRepository para crear el ViewModel
    val pokemonViewModel: PokemonViewModel = viewModel(factory = PokemonViewModelFactory(pokesRepository))

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
        composable(AppScreens.DataScreen.route) {
            DataScreen(navController, pokemonViewModel)
        }
        composable(AppScreens.EncountersScreen.route) {
            EncountersScreen(navController, pokemonViewModel)
        }
        composable(AppScreens.LeagueScreen.route) {
            LeagueScreen(navController, pokemonViewModel)
        }
    }
}
