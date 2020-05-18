package br.com.ebss.desafio_android_eduardo_seguro.ui.comic

import android.os.Build
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.ebss.desafio_android_eduardo_seguro.MainActivity
import br.com.ebss.desafio_android_eduardo_seguro.R
import br.com.ebss.desafio_android_eduardo_seguro.model.comic.Comic
import br.com.ebss.desafio_android_eduardo_seguro.utils.ImageUtils
import kotlinx.android.synthetic.main.comic_fragment.*

class ComicFragment(private val comic: Comic) : Fragment() {

    companion object {
        fun newInstance(comic: Comic) = ComicFragment(comic)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.comic_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fillComicInfo()
        iv_comic_back_arrow.setOnClickListener { onBackPressed() }
    }

    private fun fillComicInfo() {
        tv_comic_name.text = comic.title
        val description = if (comic.description.isNullOrBlank()) {
            "Sem informações disponíveis para essa HQ"
        } else {
            comic.description
        }
        tv_comic_description.text = description
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tv_comic_description.justificationMode = Layout.JUSTIFICATION_MODE_INTER_WORD
        }
        iv_comic_image.clipToOutline = true
        ImageUtils.loadDetailed(comic.thumbnail.path, comic.thumbnail.extension, iv_comic_image, context!!)
        tv_comic_price.text = "$ ${comic.prices[0].price}"
    }

    private fun onBackPressed() {
        (activity as MainActivity?)?.onBackPressed()
    }
}