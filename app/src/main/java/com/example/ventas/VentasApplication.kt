package com.example.ventas

import android.app.Application
import androidx.room.Room
//Singleton

class VentasApplication : Application () {
    companion object {
        lateinit var database : ProductDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, ProductDatabase::class.java, "ProductDatabase")
            .build()
    }
}