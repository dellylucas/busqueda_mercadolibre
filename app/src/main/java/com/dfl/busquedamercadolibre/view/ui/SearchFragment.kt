package com.dfl.busquedamercadolibre.view.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dfl.busquedamercadolibre.databinding.FragmentSearchBinding
import com.dfl.busquedamercadolibre.utils.Constants.CODE_PERMISSION
import com.dfl.busquedamercadolibre.viewmodel.ProductsViewModelFactory
import com.dfl.busquedamercadolibre.viewmodel.SearchViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


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
            val text = binding.searchTextInputEditText.text.toString()
            when {
                requirePermission() -> showToast("Para la busqueda se requiere permiso de Internet")
                getConnection() -> showToast("Active servicio de Internet")
                text.isEmpty() -> showToast("Texto vacio")
                else -> searchByName(text)
            }
        }
        vm.result.observe(viewLifecycleOwner, {
            it?.let {
                if (it.isEmpty()) findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToItemsFragment())
                else showToast(it)
            }
        })

    }

    private fun getConnection(): Boolean {
        val cm =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.getNetworkCapabilities(cm.activeNetwork)?.hasTransport(
            NetworkCapabilities.TRANSPORT_CELLULAR
        )
        return activeNetwork == null
    }

    private fun showToast(it: String) {
        Toast.makeText(context, it, Toast.LENGTH_LONG).show()
    }

    private fun searchByName(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            vm.getItems(query)
        }
    }

    private fun requirePermission(): Boolean {
        val statePermission =
            requireContext().checkSelfPermission(Manifest.permission.INTERNET) == PackageManager.PERMISSION_DENIED
        if (statePermission) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.INTERNET),
                CODE_PERMISSION
            )
        }
        return statePermission
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        vm.result.removeObservers(this)
        vm.resetValues()
    }

}