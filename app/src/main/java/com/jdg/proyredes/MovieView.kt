package com.jdg.proyredes

import android.content.Context
import android.graphics.Movie
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide

class MovieView @JvmOverloads constructor(
    context: Context,attrs:AttributeSet? = null
):LinearLayout(context,attrs) {

    private lateinit var cover: ImageView

    init{
        val layout = LayoutInflater.from(context).inflate(R.layout.publicacion_vista,this,true)
        cover = findViewById(R.id.imagen)
    }

    fun setMovie(movie: Movie){
        Glide.with(context).load(cover).into(cover)

    }
}
