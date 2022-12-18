package com.jdg.proyredes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.floatingactionbutton.FloatingActionButton

class perfilDoc : AppCompatActivity() {

    lateinit var usersDBHelper : UsersDBHelper
    lateinit var cedula: EditText
    lateinit var nom: EditText
    lateinit var espec : EditText
    lateinit var domicilio: EditText
    lateinit var tel: EditText
    lateinit var email:EditText
    lateinit var pass:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_doc)
        usersDBHelper = UsersDBHelper(this)

        //capturamos el nombre de usuario que se nos mando desde el inicio de sesion.
        var user = intent.getStringExtra("usuarioActivo").toString()

        //Leemos los datos del usuario
        var a  = usersDBHelper.readUserDoc(user)

        //Escribir los datos del usuario en la base de datos
        cedula = findViewById(R.id.cedtxt)
        cedula.setText(a[0].cedula)
        nom = findViewById(R.id.nomDoctxt)
        nom.setText(a[0].nombre)
        espec = findViewById(R.id.especDoctxt)
        espec.setText(a[0].especialidad)
        domicilio = findViewById(R.id.domicDoctxt)
        domicilio.setText(a[0].domicilio)
        tel = findViewById(R.id.telDoctxt)
        tel.setText(a[0].telefono)
        email = findViewById(R.id.emailDoctxt)
        email.setText(a[0].email)
        pass = findViewById(R.id.passDoctxt)
        pass.setText(a[0].pass)

        //sacar datos del text view

        //Boton para actualizar datos
        var btnAct: FloatingActionButton = findViewById(R.id.btnConfirmDoc)
        btnAct.setOnClickListener(){
            var cedula2 = cedula.text.toString()
            var nom2 = nom.text.toString()
            var espec2 = espec.text.toString()
            var domicilio2 = domicilio.text.toString()
            var tel2 = tel.text.toString()
            var ema = email.text.toString()
            var pas = pass.text.toString()
            usersDBHelper.updateUserDoc(cedula2, nom2, espec2, domicilio2, tel2,ema,pas)
        }

        //boton para regresar al inicio
        var btnIni: FloatingActionButton = findViewById(R.id.btnInicioDoc)
        btnIni.setOnClickListener(){
            val intent = Intent(this,pag_inicial::class.java).apply {
                putExtra("usuarioActivo", user)
            }
            startActivity(intent)
        }
    }
}