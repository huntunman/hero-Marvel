package com.example.marvelsheroes.api

import android.telecom.Call
import com.example.marvelsheroes.models.ReturnData
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi{
    @GET("characters")

    fun getCharacters(@Query("ts") ts: String,
                      @Query("apikey") apiKey: String,
                      @Query("hash") hash: String,
                      @Query("limit") limit: String,
                      @Query("offset") offset: String): Call<ReturnData>

}

interface OnGetMarvelCallback{

    fun onSuccess(marvelResponse: ReturnData)

    fun onError()
}