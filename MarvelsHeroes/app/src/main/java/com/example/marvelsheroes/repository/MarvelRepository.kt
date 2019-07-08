package com.example.marvelsheroes.repository

import android.arch.lifecycle.LiveData
import com.example.marvelsheroes.api.OnGetMarvelCallback
import com.example.marvelsheroes.models.ReturnData

interface MarvelRepository {
    //name: String, apiKey: String, ts: String, hash: String,
    fun getCharacter(offset: Int, callback: OnGetMarvelCallback)
    fun getHeroes(): LiveData<ReturnData>

}