package com.example.candyanimation.model

import com.squareup.moshi.Json

data class ProductDetails(
    val id: Int,
    @Json(name = "img_src") val imgSrcUrl: String,
    val name: String,
    val category: String,
    val description: String,
    val ingredients: String,
    val ingredientsList: String,
    val shelfLife: String,
    val productShelfLifeMonths: Int,
    val price: Int
)