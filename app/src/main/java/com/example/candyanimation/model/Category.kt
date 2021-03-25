package com.example.candyanimation.model

import com.squareup.moshi.Json

data class Category(
    val id: Int,
    @Json(name = "img_src") val imgSrcUrl: String,
    val name: String,
    val color: Int,
    val productsCount: Int
)