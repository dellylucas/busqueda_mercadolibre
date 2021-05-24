package com.dfl.busquedamercadolibre.utils

import android.Manifest

object Constants {
    const val URL_MERCADO_LIBRE = "https://api.mercadolibre.com/sites/MCO/"
    const val URL_MERCADO_LIBRE_IMAGE = "https://http2.mlstatic.com/D_NQ_NP_"
    const val URL_MERCADO_LIBRE_IMAGE_TYPE = "-O.webp"
    const val CONDITION_USAGE = "used"

    const val TIMEOUT_SERVICE = 40L //segundos
    const val CODE_PERMISSION = 100

    val PERMISSIONS =
        arrayOf(Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE)


}