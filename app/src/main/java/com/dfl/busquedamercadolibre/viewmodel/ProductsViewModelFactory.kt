package com.dfl.busquedamercadolibre.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dfl.busquedamercadolibre.model.ProductsRepository

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class ProductsViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(
                productsRepository = ProductsRepository()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}