package br.com.ebss.desafio_android_eduardo_seguro.model.comic

import com.google.gson.annotations.SerializedName

class DataComic(
    @SerializedName("offset") val offset: Int,
    @SerializedName("limit") val limit: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("count") val count: Int,
    @SerializedName("results") val results: List<Comic>
)