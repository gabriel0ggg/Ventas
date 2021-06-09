package com.example.ventas

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(ProductEntity::class), version = 1)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao() : ProductDao
}