package br.com.ebss.desafio_android_eduardo_seguro.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.ebss.desafio_android_eduardo_seguro.MainActivity
import br.com.ebss.desafio_android_eduardo_seguro.R
import br.com.ebss.desafio_android_eduardo_seguro.adapter.CharacterListAdapter
import br.com.ebss.desafio_android_eduardo_seguro.model.character.Character
import br.com.ebss.desafio_android_eduardo_seguro.model.character.CharacterResponse
import br.com.ebss.desafio_android_eduardo_seguro.ui.dialog.LoadingDialog
import br.com.ebss.desafio_android_eduardo_seguro.utils.ViewUtils
import kotlinx.android.synthetic.main.character_list_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class CharacterListFragment : Fragment() {

    private var loadingDialog: LoadingDialog? = null
    lateinit var recyclerViewCharList: RecyclerView
    private val charList = mutableListOf<Character>()
    private lateinit var characterAdapter: CharacterListAdapter
    private var offset = 0
    private var isLoading = false
    lateinit var scrollListener: RecyclerView.OnScrollListener

    private val viewModel: CharacterListViewModel by viewModel {
        parametersOf(viewLifecycleOwner)
    }

    companion object {
        fun newInstance() = CharacterListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.character_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.characterList.observe(viewLifecycleOwner, Observer {
            updateCharacterList(it)
        })
        configRecyclerView()
        viewModel.getCharacterList(offset)
        loadingDialog = ViewUtils.showDialogProgressBar(activity!!, false)
    }

    private fun configRecyclerView() {
        recyclerViewCharList = rv_characters_list
        characterAdapter = CharacterListAdapter(
            charList,
            context!!,
            this@CharacterListFragment::openCharacterDetails
        )
        recyclerViewCharList.adapter = characterAdapter
        recyclerViewCharList.layoutManager = GridLayoutManager(context, 3)
        scrollListener = object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val gridLayoutManager = recyclerView.layoutManager as GridLayoutManager?
                if (!isLoading) {
                    if (gridLayoutManager != null && gridLayoutManager.findLastCompletelyVisibleItemPosition() == charList.size - 1) {
                        viewModel.getCharacterList(offset)
                        isLoading = true
                    }
                }
            }
        }
        recyclerViewCharList.addOnScrollListener(scrollListener)
    }

    private fun showErrorMessage() {
        Toast.makeText(
            context,
            "Erro ao carregar lista de personagens",
            Toast.LENGTH_SHORT
        )
            .show()
    }

    private fun updateCharacterList(characterResponse: CharacterResponse?) {
        ViewUtils.hideDialogProgressBar(loadingDialog)
        if (characterResponse == null) {
            showErrorMessage()
        } else {
            charList.addAll(characterResponse.data.results)
            offset = charList.size
            if (offset == characterResponse.data.total) {
                recyclerViewCharList.removeOnScrollListener(scrollListener)
            }
            tv_attribution.text = characterResponse.attributionText
            characterAdapter.notifyDataSetChanged()
        }
        isLoading = false

    }

    private fun openCharacterDetails(character: Character) {
        (activity as MainActivity).addFragment(CharacterDetailFragment.newInstance(character))
    }
}
