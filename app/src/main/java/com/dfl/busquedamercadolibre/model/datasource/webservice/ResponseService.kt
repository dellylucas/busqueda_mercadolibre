package com.dfl.busquedamercadolibre.model.datasource.webservice
import com.dfl.busquedamercadolibre.model.Product
import com.google.gson.annotations.SerializedName

data class ResponseService(
    @SerializedName("site_id")
    val siteId: String,
    @SerializedName("query")
    val query: String,
    @SerializedName("results")
    val results: List<Product>
)
