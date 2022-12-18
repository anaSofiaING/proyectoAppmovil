package com.jdg.proyredes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

class perfilPaciente : AppCompatActivity() {

    lateinit var usersDBHelper : UsersDBHelper
    lateinit var id: TextView
    lateinit var nom:EditText
    lateinit var email: EditText
    lateinit var pass : EditText
    lateinit var cond: EditText
    lateinit var enferm: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_paciente)
        usersDBHelper = UsersDBHelper(this)

        //capturamos el nombre de usuario que se nos mando desde el inicio de sesion.
        var user = intent.getStringExtra("usuarioActivo").toString()

        //Leemos los datos del usuario
        var a:MutableList<usuarioPaciente> = usersDBHelper.readUserPac(user)

        id = findViewById(R.id.idPactxt)
        id.setText(a[0].id)
        nom = findViewById(R.id.nomPactxt)
        nom.setText(a[0].nombre)
        email = findViewById(R.id.emailDoctxt)
        email.setText(a[0].correo)
        pass = findViewById(R.id.passDoctxt)
        pass.setText(a[0].contra)
        cond = findViewById(R.id.condPactxt)
        cond.setText(a[0].condi)
        enferm = findViewById(R.id.enfPactxt)
        enferm.setText(a[0].enfermedad)

        //sacar datos del text view

        //Boton para actualizar datos


        //boton para regresar al inicio
        var btnIni: FloatingActionButton = findViewById(R.id.btnInicioPac)
        btnIni.setOnClickListener(){
            val intent = Intent(this,pag_inicial::class.java).apply {
                putExtra("usuarioActivo", user)
            }
            startActivity(intent)
        }

        var btnAct = findViewById<FloatingActionButton>(R.id.btnConfirmPac)

        btnAct.setOnClickListener{
            var id2 = id.text.toString()
            var nom2 = nom.text.toString()
            var email2 = email.text.toString()
            var pass2 = pass.text.toString()
            var cond2 = cond.text.toString()
            var enferm2 = enferm.text.toString()
            usersDBHelper.updateUserPac(id2, nom2, email2, pass2, cond2, enferm2)
            Toast.makeText(this,"Usuario Actualizado",Toast.LENGTH_LONG)

            
        }
    }
}