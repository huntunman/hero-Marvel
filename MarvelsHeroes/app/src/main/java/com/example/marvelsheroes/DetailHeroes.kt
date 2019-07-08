package com.example.marvelsheroes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.marvelsheroes.ui.detailhereos.DetailHereosFragment
import com.example.marvelsheroes.models.Details

class DetailHeroes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_hereos_activity)
        if (savedInstanceState == null) {

            val details = Details(name = intent.getStringExtra("name"),
                description = intent.getStringExtra("description"),
                comics = intent.getStringExtra("comics"),
                series = intent.getStringExtra("series"),
                events = intent.getStringExtra("events"),
                stories = intent.getStringExtra("stories"),
                image = intent.getStringExtra("image"),
                linkComics = intent.getStringExtra("comiclink"),
                linkDetails = intent.getStringExtra("detail"),
                linkWiki = intent.getStringExtra("wiki"))

            supportFragmentManager.beginTransaction()
                .replace(R.id.container,
                    DetailHereosFragment.newInstance(details)
                )
                .commitNow()
        }
    }

}
