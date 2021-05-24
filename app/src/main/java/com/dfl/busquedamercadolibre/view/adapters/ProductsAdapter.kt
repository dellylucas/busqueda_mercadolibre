package com.dfl.busquedamercadolibre.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dfl.busquedamercadolibre.R
import com.dfl.busquedamercadolibre.databinding.AdapterProductsBinding
import com.dfl.busquedamercadolibre.utils.Constants.URL_MERCADO_LIBRE_IMAGE
import com.dfl.busquedamercadolibre.utils.Constants.URL_MERCADO_LIBRE_IMAGE_TYPE
import com.dfl.busquedamercadolibre.view.uimodel.Item
import kotlin.math.roundToInt

class ProductsAdapter(private var productsList: List<Item>, val context: Context) :
    RecyclerView.Adapter<ProductsAdapter.ProductRecyclerHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ProductRecyclerHolder {
        val itemBinding = AdapterProductsBinding.inflate(LayoutInflater.from(p0.context), p0, false)
        return ProductRecyclerHolder(itemBinding)
    }

    override fun getItemCount() = this.productsList.size

    override fun onBindViewHolder(p0: ProductRecyclerHolder, p1: Int) {
        val paymentBean: Item = productsList[p1]
        p0.setParams(paymentBean)
    }

    inner class ProductRecyclerHolder(val view: AdapterProductsBinding) :
        RecyclerView.ViewHolder(view.root) {

        fun setParams(item: Item) {
            view.nameTextView.text = item.title
            view.PriceTextView.text = "COP " + item.price.roundToInt().toString()
            val url = URL_MERCADO_LIBRE_IMAGE + item.thumbnailId + URL_MERCADO_LIBRE_IMAGE_TYPE
            Glide.with(context)
                .load(url)
                .placeholder(R.mipmap.ic_load)
                .into(view.productImageView)
        }
    }
}