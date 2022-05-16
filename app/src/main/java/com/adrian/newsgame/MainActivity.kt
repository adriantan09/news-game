package com.adrian.newsgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.adrian.newsgame.model.Game
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.*
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    private val baseUrl = "https://firebasestorage.googleapis.com/v0/b/nca-dna-apps-dev.appspot.com/o/"
    private val alt = "media"
    private val token = "e36c1a14-25d9-4467-8383-a53f57ba6bfe"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(baseUrl)
            .build()

        val service = retrofit.create(GameApiService::class.java)

        service.getGame(alt, token).enqueue(object : Callback<Game> {
            override fun onResponse(call: Call<Game>, response: Response<Game>) {
                Log.i("MainActivity", response.toString())

                if (!response.isSuccessful) {
                    Toast.makeText(
                        this@MainActivity,
                        getString(R.string.api_fail_message),
                        Toast.LENGTH_LONG
                    ).show()
                }

            }

            override fun onFailure(call: Call<Game>, t: Throwable) {
                Log.i("MainActivity", t.message ?: "No message")
            }
        })

    }

}