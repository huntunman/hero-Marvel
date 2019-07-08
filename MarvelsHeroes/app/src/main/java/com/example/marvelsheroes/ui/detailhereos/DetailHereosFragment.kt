package com.example.marvelsheroes.ui.detailhereos

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marvelsheroes.R
import com.example.marvelsheroes.models.Details
import kotlinx.android.synthetic.main.carousel_layout.*
import kotlinx.android.synthetic.main.detail_hereos_fragment.*

class DetailHereosFragment : Fragment(), DetailsAdapter.DetailsListener {

    companion object {
        lateinit var detalhes : Details

        fun newInstance(details: Details): DetailHereosFragment{
            this.detalhes = details
            return DetailHereosFragment()
        }
    }


    private lateinit var adapter: DetailsAdapter
    private lateinit var viewModel: DetailHereosViewModel
    // private lateinit var listLinks: MutableList<Links>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.detail_hereos_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailHereosViewModel::class.java)

        adapter = DetailsAdapter(mutableListOf(), this)
        recycler_links.layoutManager = LinearLayoutManager(context)
        recycler_links.adapter = adapter

        viewModel.configureScreen(detalhes, image_thumbnail, text_name, text_description,
            text_comics_value, text_events_value, text_series_value, text_stories_value)

        adapter.updateList(viewModel.populateLinkList(detalhes.linkComics,
            detalhes.linkDetails,
            detalhes.linkWiki,
            context))
    }

    override fun openHyperLink(links: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(links))
        startActivity(browserIntent)
    }

}
