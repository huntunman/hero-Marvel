package com.example.marvelsheroes.ui.detailhereos

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.example.marvelsheroes.R
import com.ramat.origin.detailsmarvelmodule.model.Details
import com.ramat.origin.detailsmarvelmodule.model.Links
import com.squareup.picasso.Picasso

class DetailHereosViewModel : ViewModel() {
    var listLinks: ArrayList<Links> = ArrayList()

    fun configureScreen(
        details: Details,
        image_thumbnail: ImageView,
        text_name: TextView,
        text_description: TextView,
        text_comics_value: TextView,
        text_events_value: TextView,
        text_series_value: TextView,
        text_stories_value: TextView
    ) {

        Picasso.get().load(details.image).into(image_thumbnail)
        text_name.text = details.name
        text_description.text = details.description
        text_comics_value.text = details.comics
        text_events_value.text = details.events
        text_series_value.text = details.series
        text_stories_value.text = details.stories

    }

    fun populateLinkList(
        comics: String,
        details: String,
        wiki: String,
        context: Context?
    ) : MutableList<Links> {
        val linkComic = context?.getString(R.string.comic)?.let { Links(it, comics) }
        val linkDetails = context?.getString(R.string.details)?.let { Links(it, details) }
        val linkWiki = context?.getString(R.string.wiki)?.let{Links(it, wiki)}

        if (linkComic != null) {
            listLinks.add(linkComic)
        }
        if (linkDetails != null) {
            listLinks.add(linkDetails)
        }
        if (linkWiki != null) {
            listLinks.add(linkWiki)
        }

        return listLinks
    }
}
