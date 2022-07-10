package com.adrian.newsgame.network

import com.adrian.newsgame.model.Game
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL = "https://firebasestorage.googleapis.com/v0/b/nca-dna-apps-dev.appspot.com/o/"

fun setHTTPLogger() : OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return Builder().addInterceptor(interceptor).build()
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
//    .client(setHTTPLogger())
    .build()

interface GameService {
    @GET("game.json?")
    suspend fun getGame(@Query("alt") alt : String, @Query("token") token : String) : Game
}

object GameApi {
    val retrofitService: GameService by lazy {
        retrofit.create(GameService::class.java)
    }
}
