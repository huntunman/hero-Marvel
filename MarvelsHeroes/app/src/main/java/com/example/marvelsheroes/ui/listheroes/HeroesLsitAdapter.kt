package com.example.marvelsheroes.ui.listheroes

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marvelsheroes.R
import com.squareup.picasso.Picasso
import com.example.marvelsheroes.models.Result
import kotlinx.android.synthetic.main.list_heroes_items.view.*

class MarvelCharacterAdapter(private val character: MutableList<Result>,
private val listener: AdapterListener
):
RecyclerView.Adapter<MarvelCharacterAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.activity_main))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(character[position])
    }

    override fun getItemCount() = character.size

    fun updateList(character: MutableList<Result>){
        this.character.clear()
        this.character.addAll(character)
        notifyDataSetChanged()
    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private lateinit var char: Result

        fun bind(result: Result){
            this.char = result

            Picasso.get()
                .load(result.thumbnail.path + "." + result.thumbnail.extension)
                .into(itemView.image_thumbnail)
            itemView.text_name.text = result.name

            itemView.image_thumbnail.setOnClickListener { listener.showDetails(char) }

        }

    }

    interface AdapterListener{
        fun showDetails(char: Result)
    }
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}