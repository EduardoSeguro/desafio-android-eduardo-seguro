package br.com.ebss.desafio_android_eduardo_seguro.model.character

import br.com.ebss.desafio_android_eduardo_seguro.model.Thumbnail
import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("modified") val modified: String,
    @SerializedName("thumbnail") val thumbnail: Thumbnail,
    @SerializedName("resourceURI") val resourceURI: String
)