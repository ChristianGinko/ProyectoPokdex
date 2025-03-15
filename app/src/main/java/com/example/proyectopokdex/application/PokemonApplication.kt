package com.example.proyectopokdex.application

import android.app.Application
import com.example.proyectopokdex.Database.AppContainer
import com.example.proyectopokdex.Database.AppDataContainer

class PokemonApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}