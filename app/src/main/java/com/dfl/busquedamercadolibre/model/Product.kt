package com.dfl.busquedamercadolibre.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("price")
    val price: Float,
)