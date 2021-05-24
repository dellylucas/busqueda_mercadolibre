package com.dfl.busquedamercadolibre.model.datasource.webservice

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IAPIService {
    @GET("search")
    suspend fun getProducts(@Query(value = "q") keySearch: String): Response<ResponseService>
}