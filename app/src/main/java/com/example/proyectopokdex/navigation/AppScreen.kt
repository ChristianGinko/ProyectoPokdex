package com.example.proyectopokdex.navigation

sealed class AppScreens(val route: String) {
    object SplashScreen : AppScreens("splash_screen")
    object MainScreen : AppScreens("main_screen")
    object DataScreen : AppScreens("data_screen")
    object EncountersScreen : AppScreens("encounters_screen")
}