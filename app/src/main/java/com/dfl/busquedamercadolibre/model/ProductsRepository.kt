package com.dfl.busquedamercadolibre.model

import com.dfl.busquedamercadolibre.model.datasource.IProductsDataSource
import com.dfl.busquedamercadolibre.model.datasource.webservice.IAPIService
import com.dfl.busquedamercadolibre.model.datasource.webservice.SearchRest
import com.dfl.busquedamercadolibre.model.mappers.ProductDTO
import com.dfl.busquedamercadolibre.utils.DataResult
import com.dfl.busquedamercadolibre.view.uimodel.Item

class ProductsRepository : IProductsDataSource {

    override suspend fun getProducts(keySearch: String): DataResult<List<Item>> {
        val call = SearchRest.getRetrofitClient()!!
            .create(IAPIService::class.java)

        val result = call
            .getProducts(keySearch)

        return if (result.isSuccessful && result.body() != null) {
            DataResult.Success(ProductDTO.getUIItems(result.body()!!.results))
        } else {
            val error = result as DataResult.Error
            throw Exception(error.exception.toString(), error.exception)
        }
    }
}