package com.example.marvelsheroes.ui.detailhereos

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marvelsheroes.R
import com.ramat.origin.detailsmarvelmodule.model.Links
import kotlinx.android.synthetic.main.link_button.view.*

class DetailsAdapter(private val list: MutableList<Links>, private val listener: DetailsListener):
    RecyclerView.Adapter<DetailsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.link_button))
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind(list[p1])
    }

    override fun getItemCount() = list.size

    fun updateList(list: List<Links>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(result: Links){

            itemView.text_link_list.text = result.type
            itemView.layout_link_list.setOnClickListener { listener.openHyperLink(result.links) }

        }
    }

    interface DetailsListener{
        fun openHyperLink(links: String)
    }
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}