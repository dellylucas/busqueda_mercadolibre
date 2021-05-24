package com.dfl.busquedamercadolibre.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dfl.busquedamercadolibre.databinding.FragmentItemsBinding
import com.dfl.busquedamercadolibre.viewmodel.ProductsViewModelFactory
import com.dfl.busquedamercadolibre.viewmodel.SearchViewModel

class ItemsFragment : Fragment() {

    // propiedad es valida entre onCreateView y onDestroyView
    private var _binding: FragmentItemsBinding? = null

    private val binding get() = _binding!!
    private lateinit var vm: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = ViewModelProvider(
            this,
            ProductsViewModelFactory()
        ).get(SearchViewModel::class.java)
        vm.items.observe(viewLifecycleOwner, {
           it
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}