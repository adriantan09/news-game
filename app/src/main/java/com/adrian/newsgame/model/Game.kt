package com.adrian.newsgame.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Game (

    @Json(name = "product")
    val product: String = "",

    @Json(name = "resultSize")
    val resultSize: String = "",

    @Json(name = "version")
    val version: String = "",

    @Json(name = "items")
    val items: List<Item> = emptyList()

)