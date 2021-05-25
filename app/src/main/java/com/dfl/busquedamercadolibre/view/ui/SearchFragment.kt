package com.dfl.busquedamercadolibre.view.ui

import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dfl.busquedamercadolibre.R
import com.dfl.busquedamercadolibre.databinding.FragmentSearchBinding
import com.dfl.busquedamercadolibre.utils.Constants.CODE_PERMISSION
import com.dfl.busquedamercadolibre.utils.Constants.PERMISSIONS
import com.dfl.busquedamercadolibre.utils.hideKeyboard
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
            requireActivity()
        ).get(SearchViewModel::class.java)
        binding.searchTextInputEditText.imeOptions = EditorInfo.IME_ACTION_SEARCH
        setListeners()
        vm.result.observe(viewLifecycleOwner, { error ->
            error?.let {
                (activity as IBaseActivity).setLoading(false)
                //si no hay error pasa a mostrar los productos de lo contrario muestra el error
                if (error.isEmpty()) {
                    findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToItemsFragment())
                    binding.searchTextInputEditText.apply {
                        text?.clear()
                        clearFocus()
                    }
                } else showToast(String.format("%s %s", it, getString(R.string.error_admin)))
            }
        })

    }

    private fun setListeners() {
        binding.searchTextInputEditText.setOnEditorActionListener { v, _, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {
                val text = binding.searchTextInputEditText.text.toString()
                searchWord(text)
                requireContext().hideKeyboard(v)
            }
            true
        }
        binding.searchButton.setOnClickListener {
            val text = binding.searchTextInputEditText.text.toString()
            searchWord(text)
            requireContext().hideKeyboard(it)
        }
    }

    /**
     * al presionar boton de busqueda determina uina accion segun las condiciones
     * -comprueba los permisos
     * -comprueba el internet
     * -comprueba que el texto no ete vacio
     * -si se cumplen las anteriores busca la informacion(productos) hacerca de la palabra
     */
    private fun searchWord(keySearch: String) {
        when {
            requirePermission() -> showToast(getString(R.string.requiered_internet))
            getConnection() -> showToast(getString(R.string.activate_network))
            keySearch.isEmpty() -> showToast(getString(R.string.empty_text))
            else -> searchByName(keySearch)
        }
    }

    /**
     * Comprueba si hay conexion a internet
     */
    private fun getConnection(): Boolean {
        val cm =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.getNetworkCapabilities(cm.activeNetwork)?.hasTransport(
            NetworkCapabilities.TRANSPORT_CELLULAR
        )
        return activeNetwork == null
    }

    /**
     * muestra mensaje instantaneo al usuario
     */
    private fun showToast(it: String) {
        Toast.makeText(context, it, Toast.LENGTH_LONG).show()
    }

    /**
     * Busca en la fuente de datos los items o productos a mostrar que se relacionen con la palabra escrita
     */
    private fun searchByName(query: String) {
        (activity as IBaseActivity).setLoading(true)
        vm.getItems(query)
    }

    /**
     * comprueba y si es el caso solicitqa permisos necesarios
     */
    private fun requirePermission(): Boolean {
        val statePermission =
            PERMISSIONS.map { requireContext().checkSelfPermission(it) }
                .contains(PackageManager.PERMISSION_DENIED)

        if (statePermission) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                PERMISSIONS,
                CODE_PERMISSION
            )
        }
        return statePermission
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        vm.result.removeObservers(this)
        vm.resetValues()
    }
}