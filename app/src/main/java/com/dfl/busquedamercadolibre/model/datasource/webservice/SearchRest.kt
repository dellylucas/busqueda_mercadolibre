package com.dfl.busquedamercadolibre.model.datasource.webservice

import com.dfl.busquedamercadolibre.utils.Constants.TIMEOUT_SERVICE
import com.dfl.busquedamercadolibre.utils.Constants.URL_MERCADO_LIBRE
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class SearchRest {
    companion object {
        fun getRetrofitClient(): Retrofit? = Retrofit.Builder()
            .baseUrl(URL_MERCADO_LIBRE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build()

        private fun getOkHttpClient() = OkHttpClient().newBuilder()
            .connectTimeout(TIMEOUT_SERVICE, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SERVICE, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SERVICE, TimeUnit.SECONDS)
            .addInterceptor(getLoginInterceptor())
            .build()

        private fun getLoginInterceptor() =
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }
}