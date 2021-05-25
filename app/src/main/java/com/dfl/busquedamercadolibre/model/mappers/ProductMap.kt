package com.dfl.busquedamercadolibre.model.mappers

import android.util.Log
import com.dfl.busquedamercadolibre.model.Product
import com.dfl.busquedamercadolibre.utils.Constants.CONDITION_USAGE
import com.dfl.busquedamercadolibre.utils.Constants.LOG_WORD
import com.dfl.busquedamercadolibre.utils.ECondition
import com.dfl.busquedamercadolibre.view.uimodel.Item

object ProductMap {
    fun getItems(products: List<Product>): List<Item> {
        return products.mapNotNull {
            try {
                Item(
                    id = it.id,
                    title = it.title,
                    price = it.price,
                    condition = if (it.condition == CONDITION_USAGE) ECondition.USED else ECondition.NEW,
                    link = it.permalink,
                    thumbnailId = it.thumbnailId
                )
            } catch (e: Exception) {
                Log.e("$LOG_WORD - ProductMap- getItems", "$e; item error: $it")
                //si hay algun error en mapeo se deja el log y no se carga el item
                null
            }
        }
    }
}