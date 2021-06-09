package com.example.ventas

interface OnClickListener {
    fun onCLick (productEntity : ProductEntity)
    fun onFavoriteProduct(productEntity: ProductEntity)
    fun onDelete(productEntity: ProductEntity)
}