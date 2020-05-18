package br.com.ebss.desafio_android_eduardo_seguro.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

object ImageUtils {

    fun loadThumb(url: String, extension: String, view: ImageView, context: Context) {
        Glide.with(context)
            .load("${url}/portrait_small.${extension}")
            .into(view)
    }

    fun loadDetailed(url: String, extension: String, view: ImageView, context: Context) {
        Glide.with(context)
            .load("${url}/portrait_fantastic.${extension}")
            .into(view)
    }

}