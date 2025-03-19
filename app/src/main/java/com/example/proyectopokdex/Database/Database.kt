package com.example.proyectopokdex.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.proyectopokdex.entities.MyPoke

@Database(entities = [MyPoke::class], version = 2, exportSchema = false)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao

    companion object {
        @Volatile
        private var Instance: PokemonDatabase? = null

        fun getDatabase(context: Context): PokemonDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, PokemonDatabase::class.java, "pokemon_database")
                    //Fallback hace que si se destruye la base de datos, se cree una nueva (por cambios, para evitar migracion)
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}