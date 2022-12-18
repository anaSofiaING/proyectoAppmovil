package com.jdg.proyredes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class nuevaPublicacion : AppCompatActivity() {

    lateinit var usersDBHelper : UsersDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_publicacion)

        usersDBHelper = UsersDBHelper(this)

        var titulo2:EditText = findViewById(R.id.editTextTextTitulo)
        var tema2:EditText = findViewById(R.id.editTextTextTema)
        var desc2:EditText = findViewById(R.id.editTextTextDesc)
        var url2:EditText = findViewById(R.id.editTextTextURL)

        println("ttt"+titulo2)

        var titulo = titulo2.text
        var tema = tema2.text
        var desc = desc2.text
        var url = url2.text

        println("Info "+titulo+tema+desc+url)

        var btn:Button = findViewById(R.id.btnPub)

        btn.setOnClickListener{
            addNew(titulo.toString(), tema.toString(), desc.toString(), url.toString())
            val intent = Intent(this,pag_inicial::class.java)
            startActivity(intent)
        }
    }

    fun addNew(titulo2:String, tema2:String, desc2:String, url2:String){

        var result = usersDBHelper.add_New(UMPublic(titulo2,tema2,desc2,url2))
        //clear all edittext s


        if(result){
            Toast.makeText(this,"Publicacion Exitosa", Toast.LENGTH_LONG).show()
            val intent = Intent(this,pag_inicial::class.java)
            startActivity(intent)
            finish()
        }else{
            Toast.makeText(this,"Fallo en el registro", Toast.LENGTH_LONG).show()
        }
    }
}