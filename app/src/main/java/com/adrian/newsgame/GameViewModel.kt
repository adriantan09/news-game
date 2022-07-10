package com.adrian.newsgame

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adrian.newsgame.network.GameApi.retrofitService
import com.adrian.newsgame.model.Game
import com.adrian.newsgame.network.Response
import kotlinx.coroutines.launch

const val alt = "media"
const val token = "e36c1a14-25d9-4467-8383-a53f57ba6bfe"

class GameViewModel : ViewModel() {

    private val game = MutableLiveData<Response<Game>>()

    init { fetchGame() }

    private fun fetchGame() {
        Response.loading(null)
        viewModelScope.launch {
            try {
                game.value = Response.success(retrofitService.getGame(alt, token))
            } catch (e: Exception) {
                game.value = Response.error(e.message ?: "Something Went Wrong", null)
            }
        }
    }

    fun getGame() : MutableLiveData<Response<Game>> { return game }

}