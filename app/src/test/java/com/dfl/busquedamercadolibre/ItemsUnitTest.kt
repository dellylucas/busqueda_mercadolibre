package com.dfl.busquedamercadolibre

import com.dfl.busquedamercadolibre.model.Product
import com.dfl.busquedamercadolibre.model.mappers.ProductMap
import com.dfl.busquedamercadolibre.utils.ECondition
import com.dfl.busquedamercadolibre.view.uimodel.Item
import org.junit.Assert.*
import org.junit.Test

/**
 *  pruebas uitarias mapo de productos
 *  al no tener muchos casos de uso se comprueba el mapeo y posibles valores nulos
 */
class ItemsUnitTest {
    private var productIn = Product(
        id = "abc",
        title = "casa",
        price = 3.3f,
        condition = "used",
        permalink = "aaa",
        thumbnailId = "www"
    )

    private var itemOut = Item(
        id = "abc",
        title = "casa",
        price = 3.3f,
        condition = ECondition.USED,
        link = "aaa",
        thumbnailId = "www"
    )

    @Test
    fun mapItems() {
        val listInit = listOf(productIn)
        assert(ProductMap.getItems(listInit).first() == itemOut)
    }

    @Test
    fun mapItemCondition() {
        val listInit = listOf(productIn)
        assertSame(itemOut.condition, ProductMap.getItems(listInit).first().condition)

        val newListInit = listOf(productIn.copy(condition = "other"))
        assertNotEquals(itemOut.condition, ProductMap.getItems(newListInit).first().condition)
    }

    @Test
    fun mapItemThumbnailIdNull() {
        val newListInit = listOf(productIn.copy(thumbnailId = null))
        assertNull(ProductMap.getItems(newListInit).first().thumbnailId)
    }


}