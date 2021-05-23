package com.dfl.busquedamercadolibre.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dfl.busquedamercadolibre.databinding.FragmentSearchBinding
import com.dfl.busquedamercadolibre.viewmodel.ProductsViewModelFactory
import com.dfl.busquedamercadolibre.viewmodel.SearchViewModel


class SearchFragment : Fragment() {

    // propiedad es valida entre onCreateView y onDestroyView
    private var _binding: FragmentSearchBinding? = null

    private val binding get() = _binding!!
    private lateinit var vm: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = ViewModelProvider(
            this,
            ProductsViewModelFactory()
        ).get(SearchViewModel::class.java)

        binding.searchButton.setOnClickListener {
            vm.getItems(binding.searchTextInputEditText.text.toString())
        }
        vm.result.observe(viewLifecycleOwner, {
            it?.let {
                if (it.isEmpty()) findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToItemsFragment())
                else Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        vm.result.removeObservers(this)
        vm.resetValues()
    }

}