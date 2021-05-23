package com.dfl.busquedamercadolibre.model.mappers

import com.dfl.busquedamercadolibre.model.Product
import com.dfl.busquedamercadolibre.view.uimodel.Item

object ProductDTO {
    fun getUIItems(products: List<Product>): List<Item> {
        return products.map { Item(id = it.id) }
    }
}