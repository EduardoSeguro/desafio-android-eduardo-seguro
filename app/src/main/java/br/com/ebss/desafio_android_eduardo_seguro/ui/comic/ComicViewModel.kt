package br.com.ebss.desafio_android_eduardo_seguro.ui.comic

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import br.com.ebss.desafio_android_eduardo_seguro.model.comic.Comic
import br.com.ebss.desafio_android_eduardo_seguro.model.comic.ComicResponse

class ComicViewModel(
    private val comicRepository: ComicRepository,
    private val viewLifecycleOwner: LifecycleOwner,
    private val charId: Int,
    private val showErrorMessage: () -> Unit,
    private val showNoHQAvailableMessage: () -> Unit
) : ViewModel() {

    val comic = MutableLiveData<Comic>()

    private val comicList = mutableListOf<Comic>()
    var offset = 0
    var finishedLoading = false

    private fun addComicsToList(comics: List<Comic>) {
        comicList.addAll(comics)
    }

    fun openMostExpensiveComic() {
        if (!finishedLoading) {
            loadAllComics(offset)
        } else {
            val mostExpensiveComic = getMostExpensiveComic(comicList)
            comic.postValue(mostExpensiveComic)
        }
    }

    private fun loadAllComics(offset: Int) {
        val comicResponse = comicRepository.getComicList(offset, charId)
        comicResponse.observe(viewLifecycleOwner, Observer {
            if (it != null && it.code == 200) {
                updateComicList(it)
            } else {
                showErrorMessage()
            }
        })
    }

    private fun updateComicList(
        comicResponse: ComicResponse
    ) {
        addComicsToList(comicResponse.data.results)
        offset = comicList.size
        when {
            comicResponse.data.total == 0 -> {
                showNoHQAvailableMessage()
            }
            offset == comicResponse.data.total -> {
                finishedLoading = true
                val mostExpensiveComic = getMostExpensiveComic(comicList)
                comic.postValue(mostExpensiveComic)
            }
            else -> {
                loadAllComics(offset)
            }
        }
    }

    companion object {
        fun getMostExpensiveComic(comicList: MutableList<Comic>): Comic {
            val filteredList = comicList.filter { it.prices.isNotEmpty() }
            filteredList.forEach { it.prices.sortByDescending { x -> x.price } }
            val sortedList = filteredList.sortedByDescending { it.prices[0].price }
            return sortedList[0]
        }
    }
}