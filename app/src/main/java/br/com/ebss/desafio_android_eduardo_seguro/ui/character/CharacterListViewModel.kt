package br.com.ebss.desafio_android_eduardo_seguro.ui.character

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import br.com.ebss.desafio_android_eduardo_seguro.model.character.CharacterResponse

class CharacterListViewModel(
    private val characterListRepository: CharacterListRepository,
    private val viewLifecycleOwner: LifecycleOwner
) : ViewModel() {

    var characterList = MutableLiveData<CharacterResponse?>()

    fun getCharacterList(offset: Int) {

        val result = characterListRepository.getCharacterList(offset)
        result.observe(viewLifecycleOwner, Observer {
            characterList.postValue(it)
        })

    }

}