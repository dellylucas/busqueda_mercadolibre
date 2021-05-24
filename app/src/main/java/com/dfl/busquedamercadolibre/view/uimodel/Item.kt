package com.dfl.busquedamercadolibre.view.uimodel

import android.os.Parcelable
import com.dfl.busquedamercadolibre.utils.ECondition
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    val id: String,
    val title: String,
    val price: Float,
    val condition: ECondition,
    val link: String,
    val thumbnailId: String
) : Parcelable
