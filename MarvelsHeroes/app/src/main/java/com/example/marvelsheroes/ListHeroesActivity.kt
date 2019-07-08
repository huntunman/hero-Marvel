package com.example.marvelsheroes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.marvelsheroes.R
import com.example.marvelsheroes.ui.listheroes.ListHeroesFragment

class ListHeroesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_heroes_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListHeroesFragment.newInstance())
                .commitNow()
        }
    }

}
