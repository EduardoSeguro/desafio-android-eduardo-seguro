package br.com.ebss.desafio_android_eduardo_seguro.ui.character

import android.os.Build
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import br.com.ebss.desafio_android_eduardo_seguro.MainActivity
import br.com.ebss.desafio_android_eduardo_seguro.R
import br.com.ebss.desafio_android_eduardo_seguro.model.character.Character
import br.com.ebss.desafio_android_eduardo_seguro.model.comic.Comic
import br.com.ebss.desafio_android_eduardo_seguro.ui.comic.ComicFragment
import br.com.ebss.desafio_android_eduardo_seguro.ui.comic.ComicViewModel
import br.com.ebss.desafio_android_eduardo_seguro.ui.dialog.LoadingDialog
import br.com.ebss.desafio_android_eduardo_seguro.utils.ImageUtils
import br.com.ebss.desafio_android_eduardo_seguro.utils.ViewUtils
import kotlinx.android.synthetic.main.detail_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CharacterDetailFragment(private val character: Character) : Fragment() {

    private var loadingDialog: LoadingDialog? = null
    private val viewModel: ComicViewModel by viewModel {
        parametersOf(
            viewLifecycleOwner,
            character.id,
            this@CharacterDetailFragment::showErrorMessage,
            this@CharacterDetailFragment::showNoHQAvailableMessage
        )
    }

    companion object {
        fun newInstance(character: Character) = CharacterDetailFragment(character)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fillCharacterInfo()
        bt_char_detail_open_comic.setOnClickListener { openMostExpensiveComic() }
        iv_char_detail_back_arrow.setOnClickListener { onBackPressed() }
        viewModel.comic.observe(viewLifecycleOwner, Observer {
            openComicFragment(it)
        })
    }

    private fun fillCharacterInfo() {
        tv_char_detail_name.text = character.name
        val description = if (character.description.isNullOrBlank()) {
            "Sem informações disponíveis para esse personagem"
        } else {
            character.description
        }
        tv_char_detail_description.text = description
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tv_char_detail_description.justificationMode = Layout.JUSTIFICATION_MODE_INTER_WORD
        }
        iv_char_detail_image.clipToOutline = true
        ImageUtils.loadDetailed(character.thumbnail.path, character.thumbnail.extension, iv_char_detail_image, context!!)
    }

    private fun openMostExpensiveComic() {
        loadingDialog = ViewUtils.showDialogProgressBar(activity!!, false)
        viewModel.openMostExpensiveComic()
    }

    private fun showErrorMessage() {
        ViewUtils.hideDialogProgressBar(loadingDialog)
        Toast.makeText(
            context,
            "Erro ao carregar a HQ mais cara. Tente novamente mais tarde",
            Toast.LENGTH_SHORT
        )
            .show()
    }

    private fun showNoHQAvailableMessage() {
        ViewUtils.hideDialogProgressBar(loadingDialog)
        Toast.makeText(
            context,
            "Nenhuma HQ disponível para esse personagem",
            Toast.LENGTH_SHORT
        )
            .show()
    }

    private fun openComicFragment(comic: Comic) {
        ViewUtils.hideDialogProgressBar(loadingDialog)
        (activity as MainActivity).addFragment(ComicFragment.newInstance(comic))
    }

    private fun onBackPressed() {
        (activity as MainActivity?)?.onBackPressed()
    }
}