package com.dfl.busquedamercadolibre.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dfl.busquedamercadolibre.model.ProductsRepository
import com.dfl.busquedamercadolibre.utils.DataResult
import com.dfl.busquedamercadolibre.view.uimodel.Item

class SearchViewModel(val productsRepository: ProductsRepository) : ViewModel() {

    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> = _items

    private val _result = MutableLiveData<String?>()
    val result: LiveData<String?> = _result

    suspend fun getItems(keySearch: String) {
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