package com.jdg.proyredes

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class pag_inicial : AppCompatActivity(){

    lateinit var usersDBHelper : UsersDBHelper
    private lateinit var showAll: LinearLayout
    private lateinit var aBuscar: EditText
    lateinit var user:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pag_inicial)
        usersDBHelper = UsersDBHelper(this)

       // aBuscar= findViewById(R.id.txtBuscar)

        listar()

        //capturamos el id de usuario que se nos mando desde el inicio de sesion.
        user= intent.getStringExtra("usuarioActivo").toString()

        var btnBuscar:Button = findViewById(R.id.btnMenu)

        btnBuscar.setOnClickListener(){
            //busqueda(contenedor)
        }

        var btnPublicacion = findViewById<Button>(R.id.btnCreaPubli)

        btnPublicacion.setOnClickListener{
            val intent = Intent(this,nuevaPublicacion::class.java).apply {
                putExtra("usuarioActivo",user)
            }
            startActivity(intent)
        }

        var btnMenu = findViewById<Button>(R.id.btnMenu)

        btnMenu.setOnClickListener{
            val intent = Intent(this,Menu::class.java).apply {
                putExtra("usuarioActivo", user)
            }
            startActivity(intent)
        }
    }

    fun listar(){
        var resultado = usersDBHelper.readNews()
        val adapter = publicacionAdapter(this,resultado)
        var lista = findViewById<ListView>(R.id.listaPublicacion)
        lista.adapter = adapter

    }

    override fun onResume() {
        listar()
        super.onResume()
    }
}