package com.dfl.busquedamercadolibre.model.datasource

import com.dfl.busquedamercadolibre.utils.DataResult
import com.dfl.busquedamercadolibre.view.uimodel.Item

interface IProductsDataSource {

    fun getProducts(keySearch: String): DataResult<List<Item>>
}