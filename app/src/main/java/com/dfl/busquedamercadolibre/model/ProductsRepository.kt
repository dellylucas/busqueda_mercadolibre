package com.dfl.busquedamercadolibre.model

import com.dfl.busquedamercadolibre.model.datasource.IProductsDataSource
import com.dfl.busquedamercadolibre.model.mappers.ProductDTO
import com.dfl.busquedamercadolibre.utils.DataResult
import com.dfl.busquedamercadolibre.view.uimodel.Item

class ProductsRepository: IProductsDataSource {

    override fun getProducts(keySearch: String): DataResult<List<Item>> {
        val exam= listOf(Product(1),Product(2),Product(3))
        val result = DataResult.Success(exam)

        return if (result is DataResult.Success<*>) {
            DataResult.Success(ProductDTO.getUIItems(result.data))
        } else   DataResult.Success(ProductDTO.getUIItems(result.data))
    }
}