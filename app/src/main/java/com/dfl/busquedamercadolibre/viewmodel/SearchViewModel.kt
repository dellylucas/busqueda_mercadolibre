package com.dfl.busquedamercadolibre.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dfl.busquedamercadolibre.model.ProductsRepository
import com.dfl.busquedamercadolibre.utils.DataResult
import com.dfl.busquedamercadolibre.view.uimodel.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel : ViewModel() {

    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> = _items
    private val productsRepository = ProductsRepository()

    private val _result = MutableLiveData<String?>()
    val result: LiveData<String?> = _result

    /**
     * Obtiene los items a mostrar de la fuente de datos relacionados con la palabra de busqueda
     * @param keySearch palabra a buscar
     */
    fun getItems(keySearch: String) {
        _items.postValue(listOf())
        // can be launched in a separate asynchronous job
        CoroutineScope(Dispatchers.Main).launch {
            val result = withContext(Dispatchers.Default) {
                productsRepository.getProducts(keySearch)
            }

            if (result is DataResult.Success) {
                _items.postValue(result.data)
                _result.postValue("")
            } else {
                _result.value = (result as DataResult.Error).exception.toString()
            }
        }
    }

    /**
     * resetea valores iniciales de resultado
     */
    fun resetValues() {
        _result.postValue(null)
    }
}