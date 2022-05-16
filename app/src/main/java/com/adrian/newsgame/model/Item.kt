package com.adrian.newsgame.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Item (

    @Json(name = "correctAnswerIndex")
    val correctAnswerIndex: String = "",

    @Json(name = "imageUrl")
    val imageUrl: String = "",

    @Json(name = "standFirst")
    val standFirst: String = "",

    @Json(name = "storyUrl")
    val storyUrl: String = "",

    @Json(name = "section")
    val section: String = "",

    @Json(name = "headlines")
    val headlines: List<String> = emptyList()

)