package com.example.marvelsheroes.ui.listheroes

import android.app.Activity
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.example.marvelsheroes.ListHeroesActivity
import com.example.marvelsheroes.api.OnGetMarvelCallback
import com.example.marvelsheroes.models.ReturnData
import com.example.marvelsheroes.repository.MarvelRepository
import com.example.marvelsheroes.repository.MarvelRepositoryImpl

class ListHeroesViewModel(private val repository: MarvelRepository = MarvelRepositoryImpl()) : ViewModel() {
    private val heroesList: MutableLiveData<ReturnData> = MutableLiveData()
    fun getHeroesList() = heroesList

    fun verifyConnectivity(activity: ListHeroesActivity): Boolean {
        val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    fun getHeroes(offset: Int) {
        repository.getCharacter(offset, object : OnGetMarvelCallback {

            override fun onSuccess(response: ReturnData) {
                Log.d("Reponse", "It's Ok!!!")
                heroesList.value = response
            }

            override fun onError() {
                Log.e("ErrorViewModel", "Error in viewmodel call")
            }
        })
    }
}
