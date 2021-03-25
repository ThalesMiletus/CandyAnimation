package com.example.candyanimation.model

import com.squareup.moshi.Json

data class Product(
    val id: Int,
    @Json(name = "img_src") val imgSrcUrl: String,
    val name: String,
    val price: Int)