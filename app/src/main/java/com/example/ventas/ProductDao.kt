package com.example.ventas

import androidx.room.*

@Dao
interface ProductDao {
    @Query("SELECT * FROM ProductEntity")
    fun getAllProducts() : MutableList<ProductEntity>

    @Insert
    fun addProduct(productEntity: ProductEntity)

    @Update
    fun updateProduct(productEntity: ProductEntity)

    @Delete
    fun deleteProduct(productEntity: ProductEntity)
}