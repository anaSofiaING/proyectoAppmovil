package com.jdg.proyredes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View.inflate
import android.widget.Button
import android.widget.TextView
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Menu : AppCompatActivity() {

    lateinit var id:String
    lateinit var usersDBHelper : UsersDBHelper
    lateinit var CODIGO_DE_RESULTADO:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        //Tomamos el nombre del usurio registrado desde el inicio de sesion y lo escribimos en el TextView
        id= intent.getStringExtra("usuarioActivo").toString()
        usersDBHelper = UsersDBHelper(this)

        var busqueda = usersDBHelper.readUserPac(id)
        if(busqueda.size>0) {
            var usuario: TextView = findViewById(R.id.NomPerfil)
            usuario.setText(busqueda[0].nombre)
        }
        if(busqueda.size==0){
            var busqueda2 = usersDBHelper.readUserDoc(id)
            var usuario: TextView = findViewById(R.id.NomPerfil)
            println("Cedula"+id)
            usuario.setText(busqueda2[0].nombre)
        }
        //boton de ver perfil mandado el nombre para identificar cual usuario
        var perfil:Button = findViewById(R.id.btnVerPerf)
        perfil.setOnClickListener(){
            //Identificar si es doctor o paciente para mostrar diferente tipo de perfil

            var a:MutableList<usuarioPaciente> = usersDBHelper.readUserPac(id)
            if(a.size > 0){
                //Es paciente
                val intent = Intent(this,perfilPaciente::class.java).apply {
                    putExtra("usuarioActivo", id)
                }
                startActivity(intent)

            }
            if(a.size==0){
                //Es doctor
                val intent = Intent(this,perfilDoc::class.java).apply {
                    putExtra("usuarioActivo", id)
                }
                startActivity(intent)
            }

//            val intent = Intent(this,pag_inicial::class.java).apply {
//                putExtra("usuarioActivo", user)
//            }
//            startActivity(intent)
        }

        //Boton de cerrar sesion solo manda a la pantalla de inicio
        var cerrar:Button = findViewById(R.id.btnSalir)
        cerrar.setOnClickListener(){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        //Boton salir regresa a la pagina inicial sigue mandado cual es el nombre del usuario activo
        var salir: FloatingActionButton = findViewById(R.id.floatingActionButton2)
        salir.setOnClickListener(){
            val intent = Intent(this,pag_inicial::class.java).apply {
                putExtra("usuarioActivo", id)
            }
            startActivity(intent)
            finish()
        }
    }

}