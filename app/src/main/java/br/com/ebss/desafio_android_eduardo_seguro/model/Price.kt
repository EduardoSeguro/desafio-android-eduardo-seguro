package br.com.ebss.desafio_android_eduardo_seguro.model

import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("type") val type: String,
    @SerializedName("price") val price: Double
)