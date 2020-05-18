package br.com.ebss.desafio_android_eduardo_seguro.ui.comic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.ebss.desafio_android_eduardo_seguro.api.CharacterService
import br.com.ebss.desafio_android_eduardo_seguro.model.ApiHash
import br.com.ebss.desafio_android_eduardo_seguro.model.comic.ComicResponse
import io.reactivex.schedulers.Schedulers

class ComicRepository(
    private val characterService: CharacterService,
    private val apiHash: ApiHash
) {
    private val comicListResponse = MutableLiveData<ComicResponse?>()

    fun getComicList(offset: Int, charId: Int): LiveData<ComicResponse?> {
        characterService.getComicList(charId, apiHash.timestamp, apiHash.hash, offset)
            .subscribeOn(Schedulers.newThread())
            .subscribe(
                {
                    comicListResponse.postValue(it)
                },
                {
                    comicListResponse.postValue(null)
                }
            )
        return comicListResponse
    }
}