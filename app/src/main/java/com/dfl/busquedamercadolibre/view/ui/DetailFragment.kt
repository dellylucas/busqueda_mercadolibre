package com.dfl.busquedamercadolibre.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.dfl.busquedamercadolibre.R
import com.dfl.busquedamercadolibre.databinding.FragmentDetailBinding
import com.dfl.busquedamercadolibre.utils.Constants
import com.dfl.busquedamercadolibre.utils.ECondition
import kotlin.math.roundToInt

class DetailFragment : Fragment() {

    // propiedad es valida entre onCreateView y onDestroyView
    private var _binding: FragmentDetailBinding? = null

    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val product = args.item

        binding.titleTextView.text = product.title
        binding.stateTextView.text = String.format(
            "%s %s", getString(R.string.condition),
            if (ECondition.NEW == product.condition) getString(
                R.string.new_
            ) else getString(
                R.string.used
            )
        )
        binding.priceTextView.text = String.format("COP %s", product.price.roundToInt().toString())
        binding.linkTextView.text =
            String.format("%s %s", getString(R.string.details), product.link)
        val url =
            Constants.URL_MERCADO_LIBRE_IMAGE + product.thumbnailId + Constants.URL_MERCADO_LIBRE_IMAGE_TYPE
        Glide.with(requireContext())
            .load(url)
            .placeholder(R.mipmap.ic_load)
            .into(binding.imageAppCompatImageView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}