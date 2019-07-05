package com.example.marvelsheroes.ui.listheroes

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marvelsheroes.DetailHereos
import com.example.marvelsheroes.MarvelCharacterAdapter
import com.example.marvelsheroes.R
import kotlinx.android.synthetic.main.list_heroes_fragment.*

class ListHeroesFragment : Fragment() {

    companion object {
        fun newInstance() = ListHeroesFragment()
    }

    private var viewModel: ListHeroesViewModel = ListHeroesViewModel()
    private lateinit var adapter: MarvelCharacterAdapter
    private var offset: Int = 150

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.list_heroes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListHeroesViewModel::class.java)
        // TODO: Use the ViewModel
        recycler_view_character.
            layoutManager = GridLayoutManager(context, 2)
        recycler_view_character.
            itemAnimator = DefaultItemAnimator()
        recycler_view_character.
            adapter = adapter
        viewModel.getHeroes(offset)
        configureObservers()
    }

    fun configureObservers(){
        viewModel.getHeroesList().observe(
            this, Observer { heroes ->

                heroes?.let {

                    adapter.updateList(heroes.data.results as MutableList<Result>)

                    if (heroes.data.count > 0) {

                        updateOffset()
                    }
                }
            })
    }

    private fun updateOffset(){
        offset += 100
        viewModel.getHeroes((offset))
    }

    fun showLoading(){
        progressbar_home.visibility = View.VISIBLE
    }

    fun hideLoading(){
        progressbar_home.visibility = View.GONE
    }

    override fun showDetails(char: Result) {
        val intent = Intent(activity, DetailHereos::class.java)
        intent.putExtra("image", char.thumbnail.path + "." + char.thumbnail.extension)
        intent.putExtra("name", char.name)
        intent.putExtra("description", char.description)
        intent.putExtra("comics", char.comics.available.toString())
        intent.putExtra("series", char.series.available.toString())
        intent.putExtra("stories", char.stories.available.toString())
        intent.putExtra("events", char.events.available.toString())

        char.urls.forEach{
            intent.putExtra(it.type, it.url)
        }


        startActivity(intent)
        Log.d("CLick", "clickable no item")
    }

    override fun openHyperLink(links: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(links))
        startActivity(browserIntent)
    }
}
