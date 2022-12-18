package com.jdg.proyredes
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso


class publicacionAdapter(private val mContext: Context, private val listaPublicacion: List<UMPublic>): ArrayAdapter<UMPublic>(mContext,0,listaPublicacion){
    private lateinit var cover:ImageView

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.publicacion_vista,parent,false)

        val publ = listaPublicacion[position]

        layout.findViewById<TextView>(R.id.textAutor).text = publ.tema
        layout.findViewById<TextView>(R.id.textDesc).text = publ.cont
        layout.findViewById<TextView>(R.id.textTitulo).text = publ.tit
        //val movie =  layout.findViewById<ImageView>(R.id.imagen)

        val imageUri = publ.img
        println("URL "+imageUri)
        val ivBasicImage = layout.findViewById<ImageView>(R.id.imagen)
        Picasso.with(mContext).load(imageUri).into(ivBasicImage)

        //setMovie()

        return layout
    }

    fun setMovie(){
        Glide.with(context).load(cover).into(cover)
    }

}