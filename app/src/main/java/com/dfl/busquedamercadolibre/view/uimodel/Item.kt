package com.dfl.busquedamercadolibre.view.uimodel

data class Item(
    val id: String,
    val title: String,
    val price: Float,
    val condition: String,
    val link: String,
    val thumbnail: String,
    val thumbnailId: String
)
