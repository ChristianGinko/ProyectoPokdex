package com.example.proyectopokdex.navigation

import com.example.proyectopokdex.SplashScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectopokdex.MainScreen
import com.example.proyectopokdex.SplashScreen
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.example.proyectopokdex.Lista.pokes

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = AppScreens.SplashScreen.route
    ) {
        composable(AppScreens.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(AppScreens.MainScreen.route) {
            MainScreen(navController, pokes)
        }
    }
}