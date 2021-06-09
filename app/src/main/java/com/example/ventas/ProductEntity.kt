package com.example.ventas

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ProductEntity")

data class ProductEntity(@PrimaryKey(autoGenerate = true)var id: Long = 0,
                         var name : String = "",
                         var phone : String = "",
                         var website : String = "",
                         var isFavorite : Boolean  = false)
