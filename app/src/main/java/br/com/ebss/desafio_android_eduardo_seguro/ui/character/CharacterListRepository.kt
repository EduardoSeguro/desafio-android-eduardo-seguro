package br.com.ebss.desafio_android_eduardo_seguro.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.ebss.desafio_android_eduardo_seguro.api.CharacterService
import br.com.ebss.desafio_android_eduardo_seguro.model.ApiHash
import br.com.ebss.desafio_android_eduardo_seguro.model.character.CharacterResponse
import io.reactivex.schedulers.Schedulers

class CharacterListRepository(
    private val characterService: CharacterService,
    private val apiHash: ApiHash
) {
    private val charListResponse = MutableLiveData<CharacterResponse?>()

    fun getCharacterList(offset: Int): LiveData<CharacterResponse?> {
        characterService.getCharacterList(apiHash.timestamp, apiHash.hash, offset)
            .subscribeOn(Schedulers.newThread())
            .subscribe(
                {
                    charListResponse.postValue(it)
                },
                {
                    charListResponse.postValue(null)
                }
            )
        return charListResponse
    }
}