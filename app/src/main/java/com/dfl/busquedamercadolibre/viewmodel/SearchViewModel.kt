package com.dfl.busquedamercadolibre.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dfl.busquedamercadolibre.model.ProductsRepository
import com.dfl.busquedamercadolibre.utils.DataResult
import com.dfl.busquedamercadolibre.view.uimodel.Item

class SearchViewModel() : ViewModel() {

    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> = _items
    val productsRepository= ProductsRepository()
    private val _result = MutableLiveData<String?>()
    val result: LiveData<String?> = _result


    suspend fun getItems(keySearch: String) {
        _items.postValue(listOf())
        // can be launched in a separate asynchronous job
        val result = productsRepository.getProducts(keySearch)

        if (result is DataResult.Success) {
            _items.postValue(result.data)
            _result.postValue("")
        } else {
            _result.value = (result as DataResult.Error).exception.toString()
        }
    }

    fun resetValues() {
        _result.postValue(null)
    }
}