package com.adrian.newsgame

import android.widget.ImageView
import androidx.core.net.toUri
import coil.load

class Util {
    companion object {
        fun bindImage(imgView: ImageView, imgUrl: String?) {
            imgUrl?.let {
                val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
                imgView.load(imgUri) {
                    placeholder(R.drawable.loading_img)
                    error(R.drawable.broken_image)
                }
            }
        }
    }
}