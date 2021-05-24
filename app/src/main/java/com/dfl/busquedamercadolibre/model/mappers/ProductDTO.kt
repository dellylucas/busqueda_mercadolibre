package com.dfl.busquedamercadolibre.model.mappers

import com.dfl.busquedamercadolibre.model.Product
import com.dfl.busquedamercadolibre.utils.Constants.CONDITION_USAGE
import com.dfl.busquedamercadolibre.utils.ECondition
import com.dfl.busquedamercadolibre.view.uimodel.Item

object ProductDTO {
    fun getUIItems(products: List<Product>): List<Item> {
        return products.map {
            Item(
                id = it.id,
                title = it.title,
                price = it.price,
                condition = if (it.condition == CONDITION_USAGE) ECondition.USED else ECondition.NEW,
                link = it.permalink,
                thumbnailId = it.thumbnailId
            )
        }
    }
}