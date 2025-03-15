package com.example.proyectopokdex.Database

import android.app.Application

class PokemonApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}