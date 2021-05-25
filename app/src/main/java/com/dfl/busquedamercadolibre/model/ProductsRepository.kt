package com.dfl.busquedamercadolibre.model

import com.dfl.busquedamercadolibre.model.datasource.IProductsDataSource
import com.dfl.busquedamercadolibre.model.datasource.webservice.IAPIService
import com.dfl.busquedamercadolibre.model.datasource.webservice.SearchRest
import com.dfl.busquedamercadolibre.model.mappers.ProductMap
import com.dfl.busquedamercadolibre.utils.DataResult
import com.dfl.busquedamercadolibre.view.uimodel.Item

class ProductsRepository : IProductsDataSource {

    /**
     * obtiene los productos de la fuente de datos web service
     */
    override suspend fun getProducts(keySearch: String): DataResult<List<Item>> {
        val call = SearchRest.getRetrofitClient()!!
            .create(IAPIService::class.java)

        val result = call.getProducts(keySearch)

        return if (result.isSuccessful && result.body() != null)
            DataResult.Success(ProductMap.getItems(result.body()!!.results))
        else DataResult.Error(Exception(result.message() + " " + result.code()))
    }
}