package br.com.ebss.desafio_android_eduardo_seguro.utils

import androidx.lifecycle.LifecycleOwner
import br.com.ebss.desafio_android_eduardo_seguro.api.RestClient
import br.com.ebss.desafio_android_eduardo_seguro.model.ApiHash
import br.com.ebss.desafio_android_eduardo_seguro.ui.character.CharacterListRepository
import br.com.ebss.desafio_android_eduardo_seguro.ui.character.CharacterListViewModel
import br.com.ebss.desafio_android_eduardo_seguro.ui.comic.ComicRepository
import br.com.ebss.desafio_android_eduardo_seguro.ui.comic.ComicViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.*

val mainModule = module {
    factory {
        ApiHash(
            timestamp = Calendar.getInstance().timeInMillis.toString()
        )
    }

    factory {
        CharacterListRepository(
            characterService = RestClient.getClient(),
            apiHash = get()
        )
    }

    factory {
        ComicRepository(
            characterService = RestClient.getClient(),
            apiHash = get()
        )
    }

    viewModel { (viewLifecycleOwner: LifecycleOwner) ->
        CharacterListViewModel(
            characterListRepository = get(),
            viewLifecycleOwner = viewLifecycleOwner
        )
    }

    viewModel { (viewLifecycleOwner: LifecycleOwner,
                    charId: Int,
                    showErrorMessage: () -> Unit,
                    showNoHQAvailableMessage: () -> Unit) ->
        ComicViewModel(
            comicRepository = get(),
            viewLifecycleOwner = viewLifecycleOwner,
            charId = charId,
            showErrorMessage = showErrorMessage,
            showNoHQAvailableMessage = showNoHQAvailableMessage
        )
    }
}