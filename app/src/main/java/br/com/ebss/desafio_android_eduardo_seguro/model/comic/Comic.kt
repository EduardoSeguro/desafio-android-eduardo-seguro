package br.com.ebss.desafio_android_eduardo_seguro.model.comic

import br.com.ebss.desafio_android_eduardo_seguro.model.Price
import br.com.ebss.desafio_android_eduardo_seguro.model.Thumbnail
import com.google.gson.annotations.SerializedName

class Comic(
    @SerializedName("id") val id: Int,
    @SerializedName("digitalId") val digitalId: Int,
    @SerializedName("title") val title: String,
    @SerializedName("variantDescription") val variantDescription: String,
    @SerializedName("description") val description: String,
    @SerializedName("prices") val prices: MutableList<Price>,
    @SerializedName("thumbnail") val thumbnail: Thumbnail
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Comic

        if (other.id != id) return false

        return true
    }
}