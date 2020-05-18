package br.com.ebss.desafio_android_eduardo_seguro.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.ebss.desafio_android_eduardo_seguro.R
import br.com.ebss.desafio_android_eduardo_seguro.model.character.Character
import br.com.ebss.desafio_android_eduardo_seguro.utils.ImageUtils
import kotlinx.android.synthetic.main.row_character_list.view.*

class CharacterListAdapter(
    private val baseItemList: MutableList<Character>,
    private val context: Context,
    private val openCharacter: (Character) -> Unit
) : RecyclerView.Adapter<CharacterListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_character_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return baseItemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = baseItemList[position]
        holder.bindView(item, context, openCharacter)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(
            item: Character,
            context: Context,
            openCharacter: (Character) -> Unit
        ) {
            val thumbnail = itemView.iv_character_thumb
            val name = itemView.tv_character_name

            name.text = item.name
            thumbnail.clipToOutline = true
            ImageUtils.loadThumb(item.thumbnail.path, item.thumbnail.extension, thumbnail, context)
            itemView.setOnClickListener {
                openCharacter(item)
            }
        }
    }
}