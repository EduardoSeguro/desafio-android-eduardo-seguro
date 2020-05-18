package br.com.ebss.desafio_android_eduardo_seguro.model.character

import com.google.gson.annotations.SerializedName

data class DataCharacter(
    @SerializedName("offset") val offset: Int,
    @SerializedName("limit") val limit: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("count") val count: Int,
    @SerializedName("results") val results: List<Character>
)