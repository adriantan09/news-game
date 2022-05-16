package com.adrian.newsgame

import com.adrian.newsgame.model.Game
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://firebasestorage.googleapis.com/v0/b/nca-dna-apps-dev.appspot.com/o/game.json?alt=media&token=e36c1a14-25d9-4467-8383-a53f57ba6bfe"
private const val TOKEN = "e36c1a14-25d9-4467-8383-a53f57ba6bfe"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface GameApiService {
    @GET("game.json?")
    fun getGame(@Query("alt") alt : String, @Query("token") token : String) : Call<Game>
}

object GameApi {
    val retrofitService: GameApiService by lazy {
        retrofit.create(GameApiService::class.java)
    }
}
