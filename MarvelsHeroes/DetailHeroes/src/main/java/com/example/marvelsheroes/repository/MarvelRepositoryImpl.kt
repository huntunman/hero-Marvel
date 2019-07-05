package com.example.marvelsheroes.repository

import android.arch.lifecycle.MutableLiveData
import android.provider.SyncStateContract
import android.util.Log
import com.example.marvelsheroes.api.MarvelApi
import com.example.marvelsheroes.api.OnGetMarvelCallback
import com.example.marvelsheroes.models.ReturnData
import com.example.marvelsheroes.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MarvelRepositoryImpl: MarvelRepository {

    private var service: MarvelApi
    private var heroesData: MutableLiveData<ReturnData> = MutableLiveData()

    companion object {
        const val BASE_URL = "http://gateway.marvel.com/v1/public/"
        const val PUBLIC_KEY = Constants.Keys.PUBLIC_KEY
        const val PRIVATE_KEY = Constants.Keys.PRIVATE_KEY
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(MarvelApi::class.java)

    }

    override fun getHeroes() = heroesData

    private fun getMd5(ts: String): String {
        try {

            val md = MessageDigest.getInstance("MD5")

            val messageDigest = md.digest(ts.toByteArray()
                    + PRIVATE_KEY.toByteArray()
                    + PUBLIC_KEY.toByteArray())

            val no = BigInteger(1, messageDigest)

            var hashtext = no.toString(16)
            while (hashtext.length < 32) {
                hashtext = "0$hashtext"
            }
            return hashtext
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(e)
        }
    }

    override fun getCharacter(offset: Int, callback: OnGetMarvelCallback) {
        val ts = System.currentTimeMillis().toString()
        val hash = getMd5(ts)

        service.getCharacters(ts, Constants.Keys.PUBLIC_KEY, hash, offset = offset.toString())
            .enqueue(object : Callback<ReturnData> {
                override fun onResponse(call: Call<ReturnData>, response: Response<ReturnData>) {

                    if (response.isSuccessful){
                        if (response.body() != null){
                            heroesData.postValue(response.body())
                            callback.onSuccess(response.body()!!)
                        } else {
                            callback.onError()
                            Log.e("Response", " response null")
                        }

                    } else {
                        callback.onError()
                        Log.e("Response", response.raw().networkResponse().toString())
                    }

                }

                override fun onFailure(call: Call<ReturnData>, t: Throwable) {
                    callback.onError()
                    t.printStackTrace()
                    Log.e("Response", javaClass.simpleName + " not response 2 " + t)
                }
            })
    }
}