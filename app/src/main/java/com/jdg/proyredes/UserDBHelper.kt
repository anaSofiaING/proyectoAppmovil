package com.jdg.proyredes

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

import java.util.ArrayList

class UsersDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,
    null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL(
            String.format(
                "CREATE TABLE " + DBContract.userPaciente.TABLE_NAME + " (" +
                        DBContract.userPaciente.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        DBContract.userPaciente.COLUMN_NOM + " TEXT," +
                        DBContract.userPaciente.COLUMN_EMAIL + " TEXT," +
                        DBContract.userPaciente.COLUMN_PASS + " TEXT," +
                        DBContract.userPaciente.COLUMN_COND + " TEXT," +
                        DBContract.userPaciente.COLUMN_ENFERM + " TEXT," +
                        DBContract.userPaciente.COLUMN_TELEFONO + " TEXT," +
                        DBContract.userPaciente.COLUMN_EDAD + " INTEGER)"
            )
        )
        db.execSQL(
            String.format(
                "CREATE TABLE " + DBContract.userDoctor.TABLE_NAME + " (" +
                        DBContract.userDoctor.COLUMN_CEDULA + " TEXT PRIMARY KEY," +
                        DBContract.userDoctor.COLUMN_NOM + " TEXT," +
                        DBContract.userDoctor.COLUMN_ESPEC + " TEXT," +
                        DBContract.userDoctor.COLUMN_DOMICILIO + " TEXT," +
                        DBContract.userDoctor.COLUMN_TELEFONO + " TEXT," +
                        DBContract.userDoctor.COLUMN_EMAIL + " TEXT," +
                        DBContract.userDoctor.COLUMN_PASS + " TEXT," +
                        DBContract.userDoctor.COLUMN_DISP + " TEXT)"
            )
        )
        db.execSQL(
            String.format(
                "CREATE TABLE " + DBContract.userPubli.TABLE_NAME + " (" +
                        DBContract.userPubli.COLUMN_TITULO + " TEXT PRIMARY KEY," +
                        DBContract.userPubli.COLUMN_TEMA + " TEXT," +
                        DBContract.userPubli.COLUMN_CONT + " TEXT," +
                        DBContract.userPubli.COLUMN_URLIMG + " TEXT)"
            )
        )
        //db.execSQL(SQL_CREATE_ENTRIES_DOC)
        //db.execSQL(SQL_CREATE_ENTRIES_PAC)
        //db.execSQL(SQL_CREATE_PUBLIC)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // En caso de actualizar la version de BD elimina lo anterior, para iniciar otra

        //SE TENDRA QUE AGREGAR TODAS LAS BASES DE DATOS
        db.execSQL(SQL_DELETE_ENTRIES_DOC)
        db.execSQL(SQL_DELETE_ENTRIES_PAC)
        db.execSQL(SQL_DELETE_PUBLIC)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    @Throws(SQLiteConstraintException::class)
    fun insertUserPaciente(user: UserModel): Boolean {
        //Obtenemos la base de datos para escribir
        val db = writableDatabase
        // Creamos un ContentValues, para asignar los valores a insertar
        val values = ContentValues()
        //values.put(DBContract.userPaciente.COLUMN_ID, "")
        values.put(DBContract.userPaciente.COLUMN_NOM, user.nombre)
        values.put(DBContract.userPaciente.COLUMN_EMAIL, user.correo)
        values.put(DBContract.userPaciente.COLUMN_PASS, user.contra)
        values.put(DBContract.userPaciente.COLUMN_COND, "")
        values.put(DBContract.userPaciente.COLUMN_ENFERM, "")
        values.put(DBContract.userPaciente.COLUMN_TELEFONO, "")
        values.put(DBContract.userPaciente.COLUMN_EDAD, "")

        //Insertamos el nuevo registro
        val newRowId = db.insert(DBContract.userPaciente.TABLE_NAME, null, values)

        return true
    }

    @Throws(SQLiteConstraintException::class)
    fun insertUserDoctor(user: UserModel): Boolean {
        //Obtenemos la base de datos para escribir
        val db = writableDatabase
        // Creamos un ContentValues, para asignar los valores a insertar
        val values = ContentValues()
        values.put(DBContract.userDoctor.COLUMN_CEDULA, user.texto)
        values.put(DBContract.userDoctor.COLUMN_NOM, user.nombre)
        values.put(DBContract.userDoctor.COLUMN_ESPEC, "")
        values.put(DBContract.userDoctor.COLUMN_DOMICILIO, "")
        values.put(DBContract.userDoctor.COLUMN_TELEFONO, "")
        values.put(DBContract.userDoctor.COLUMN_EMAIL, user.correo)
        values.put(DBContract.userDoctor.COLUMN_PASS, user.contra)
        values.put(DBContract.userDoctor.COLUMN_DISP, "")

        //Insertamos el nuevo registro
        val newRowId = db.insert(DBContract.userDoctor.TABLE_NAME, null, values)

        return true
    }

    @Throws(SQLiteConstraintException::class)
    fun updateUserPac(id: String, nom: String, email: String, pass: String, cond: String, enfer: String){
        //Obtenemos la base de datos para escribir
        val db = writableDatabase
        // Creamos el query para buscar llave a modificar
        val registro = ContentValues()
        registro.put(DBContract.userPaciente.COLUMN_NOM, nom)
        registro.put(DBContract.userPaciente.COLUMN_EMAIL, email)
        registro.put(DBContract.userPaciente.COLUMN_PASS, pass)
        registro.put(DBContract.userPaciente.COLUMN_COND, cond)
        registro.put(DBContract.userPaciente.COLUMN_ENFERM, enfer)

        // Modificamos el registro

            db.update(
                DBContract.userPaciente.TABLE_NAME, registro,
                "id = '${id}'", null
            )

       // db.execSQL(String.format("UPDATE "+DBContract.userPaciente.TABLE_NAME+" SET nombre='"+nom+"', email='"+email+"', pass='"+pass+"', cond="+cond+", enfermedad='"+enfer +"' WHERE id='"+id+"'"))
        println("Paciente actualizado")
    }

    fun updateUserDoc(cedula: String, nom: String, espec: String,
                      domicilio: String, tel: String,email:String,pass: String): Boolean {
        //Obtenemos la base de datos para escribir
        val db = writableDatabase
        // Creamos el query para buscar llave a modificar
        val registro = ContentValues()
        registro.put(DBContract.userDoctor.COLUMN_NOM, nom)
        registro.put(DBContract.userDoctor.COLUMN_ESPEC, espec)
        registro.put(DBContract.userDoctor.COLUMN_DOMICILIO, domicilio)
        registro.put(DBContract.userDoctor.COLUMN_TELEFONO, tel)
        registro.put(DBContract.userDoctor.COLUMN_EMAIL,email)
        registro.put(DBContract.userDoctor.COLUMN_PASS,pass)

        // Modificamos el registro
        db.update(
            DBContract.userDoctor.TABLE_NAME, registro,
            "cedula = '${cedula}'", null
        )

        return true
    }

    //leer el usuario doctor
    @SuppressLint("Range")
    fun readUserDocLogin(correo: String, contra: String): MutableList<UserModel> {
        val doctor = mutableListOf<UserModel>()
        val db = writableDatabase
        var cursor: Cursor? = null
            cursor = db.rawQuery(
                "SELECT * FROM " + DBContract.userDoctor.TABLE_NAME
                        + " WHERE " + DBContract.userDoctor.COLUMN_EMAIL + "='"
                        + correo + "'"
                        + " AND " + DBContract.userDoctor.COLUMN_PASS + "='"
                        + contra + "'", null
            )

        var nombre: String
        var texto: String
        with(cursor) {
            while (moveToNext()) {
                //nombre = this?.getLong(getColumnIndexOrThrow(DBContract.userDoctor.COLUMN_NOM)).toString()
                nombre = cursor.getString(cursor.getColumnIndex(DBContract.userDoctor.COLUMN_NOM))
                //en esta ocasion usaremos el campo texto para poder guardar la cedula y asi no crear otro modelo
                texto = cursor.getString(cursor.getColumnIndex(DBContract.userDoctor.COLUMN_CEDULA))
                doctor.add(UserModel(correo, contra, nombre, texto))
            }
        }
        println("---"+doctor)
        return doctor
    }

    //Leer si pusieron correo y contra correcta
    @SuppressLint("Range")
    fun readUserPacLogin(correo: String, contra: String): ArrayList<UserModel> {
        val paciente = ArrayList<UserModel>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(
                "select * from " + DBContract.userPaciente.TABLE_NAME
                        + " WHERE " + DBContract.userPaciente.COLUMN_EMAIL + "='"
                        + correo + "'"
                        + " AND " + DBContract.userPaciente.COLUMN_PASS + "='"
                        + contra + "'", null
            )
        } catch (e: SQLiteException) {
            // si la BBD no esta creada, la creamos
            db.execSQL(SQL_CREATE_ENTRIES_DOC)
        }

        var nombre: String
        var id: String
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                nombre = cursor.getString(cursor.getColumnIndex(DBContract.userPaciente.COLUMN_NOM))
                //en esta ocasion usaremos el campo texto para poder guardar la cedula y asi no crear otro modelo
                id = cursor.getString(cursor.getColumnIndex(DBContract.userPaciente.COLUMN_ID))

                paciente.add(UserModel(correo, contra, nombre, id))
                cursor.moveToNext()
            }
        }
        return paciente
    }

    @SuppressLint("Range")
    fun readUserPac(nom: String): MutableList<usuarioPaciente> {
        val users = mutableListOf<usuarioPaciente>()
        val db = writableDatabase
        var cursor: Cursor? = null
            cursor = db.rawQuery("select * from " + DBContract.userPaciente.TABLE_NAME
                    + " WHERE " + DBContract.userPaciente.COLUMN_ID + "='"
                    + nom + "'", null)
        var id: String
        var nom:String
        var email: String
        var pass: String
        var cond: String
        var enferm: String
        var edad: String

        with(cursor) {
            while (moveToNext()) {
                id = cursor.getString(cursor.getColumnIndex(DBContract.userPaciente.COLUMN_ID))
                nom = cursor.getString(cursor.getColumnIndex(DBContract.userPaciente.COLUMN_NOM))
                email = cursor.getString(cursor.getColumnIndex(DBContract.userPaciente.COLUMN_EMAIL))
                pass = cursor.getString(cursor.getColumnIndex(DBContract.userPaciente.COLUMN_PASS))
                cond = cursor.getString(cursor.getColumnIndex(DBContract.userPaciente.COLUMN_COND))
                enferm = cursor.getString(cursor.getColumnIndex(DBContract.userPaciente.COLUMN_ENFERM))
                edad = cursor.getString(cursor.getColumnIndex(DBContract.userPaciente.COLUMN_EDAD))
                users.add(usuarioPaciente(id,email, pass,nom,cond,enferm,edad))
            }
        }
        return users
    }

    @SuppressLint("Range")
    fun readUserDoc(nom: String): MutableList<usuarioDoctor> {
        val users = mutableListOf<usuarioDoctor>()
        val db = writableDatabase
        var cursor: Cursor? = null

            cursor = db.rawQuery("select * from " + DBContract.userDoctor.TABLE_NAME
                    + " WHERE " + DBContract.userDoctor.COLUMN_CEDULA + "='"
                    + nom + "'", null)


        var cedula: String
        var nom:String
        var espec: String
        var domicilio: String
        var tel: String
        var email: String
        var pass : String

        with (cursor) {
            while (moveToNext()) {
                cedula = cursor.getString(cursor.getColumnIndex(DBContract.userDoctor.COLUMN_CEDULA))
                nom = cursor.getString(cursor.getColumnIndex(DBContract.userDoctor.COLUMN_NOM))
                espec = cursor.getString(cursor.getColumnIndex(DBContract.userDoctor.COLUMN_ESPEC))
                domicilio = cursor.getString(cursor.getColumnIndex(DBContract.userDoctor.COLUMN_DOMICILIO))
                tel = cursor.getString(cursor.getColumnIndex(DBContract.userDoctor.COLUMN_TELEFONO))
                email = cursor.getString(cursor.getColumnIndex(DBContract.userDoctor.COLUMN_EMAIL))
                pass = cursor.getString(cursor.getColumnIndex(DBContract.userDoctor.COLUMN_PASS))
                users.add(usuarioDoctor(cedula,nom,espec,domicilio,tel,email,pass))
            }
        }
        return users
    }

    //para agregar las publicaciones
    @Throws(SQLiteConstraintException::class)
    fun add_New(public: UMPublic): Boolean {
        //Obtenemos la base de datos para escribir
        val db = writableDatabase
        // Creamos un ContentValues, para asignar los valores a insertar
        val values = ContentValues()
        values.put(DBContract.userPubli.COLUMN_TITULO, public.tit)
        values.put(DBContract.userPubli.COLUMN_TEMA, public.tema)
        values.put(DBContract.userPubli.COLUMN_CONT, public.cont)
        values.put(DBContract.userPubli.COLUMN_URLIMG, public.img)

        //Insertamos el nuevo registro
        val newRowId = db.insert(DBContract.userPubli.TABLE_NAME, null, values)

        return true
    }

    //Leer todas las publicaciones
    @SuppressLint("Range")
    fun readNews(): MutableList<UMPublic> { //crearemos un modelo para las public.
        val news = mutableListOf<UMPublic>()
        val db = writableDatabase
        var cursor: Cursor? = null
            cursor = db.rawQuery("select * from " + DBContract.userPubli.TABLE_NAME, null)

        var tit: String
        var tema: String
        var info: String
        var img: String
        with(cursor) {
            while (moveToNext()) {
                tit = cursor.getString(cursor.getColumnIndex(DBContract.userPubli.COLUMN_TITULO))
                tema = cursor.getString(cursor.getColumnIndex(DBContract.userPubli.COLUMN_TEMA))
                info = cursor.getString(cursor.getColumnIndex(DBContract.userPubli.COLUMN_CONT))
                img = cursor.getString(cursor.getColumnIndex(DBContract.userPubli.COLUMN_URLIMG))

                news.add(UMPublic(tit, tema, info, img))
            }
        }
        return news
    }

    //Leer una noticia por palabra clave
    @SuppressLint("Range")
    fun read_new(palabra: String): ArrayList<UMPublic>{
        val news = ArrayList<UMPublic>()
        //convertimos la palabra a mayus y minus para buscar todos los
        //resultados posibles
        var min=palabra.lowercase()
        var mayus=palabra.uppercase()

        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(
                "select * from " + DBContract.userPubli.TABLE_NAME
                        + " WHERE " + DBContract.userPubli.COLUMN_TITULO + "='"
                        + palabra + "'"
                        + " OR " + DBContract.userPubli.COLUMN_TITULO + "='"
                        + min + "'"
                        + " OR " + DBContract.userPubli.COLUMN_TITULO + "='"
                        + mayus + "'"
                        + " OR " + DBContract.userPubli.COLUMN_TEMA + "='"
                        + min + "'"
                        + " OR " + DBContract.userPubli.COLUMN_TEMA + "='"
                        + mayus + "'"
                        + " OR " + DBContract.userPubli.COLUMN_TEMA + "='"
                        + palabra + "'", null
            )
        } catch (e: SQLiteException) {
            // si la BBD no esta creada, la creamos
            db.execSQL(SQL_CREATE_PUBLIC)
        }

        var tit: String
        var tema: String
        var info: String
        var img: String
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                tit = cursor.getString(cursor.getColumnIndex(DBContract.userPubli.COLUMN_TITULO))
                tema = cursor.getString(cursor.getColumnIndex(DBContract.userPubli.COLUMN_TEMA))
                info = cursor.getString(cursor.getColumnIndex(DBContract.userPubli.COLUMN_CONT))
                img = cursor.getString(cursor.getColumnIndex(DBContract.userPubli.COLUMN_URLIMG))

                news.add(UMPublic(tit, tema, info, img))
                cursor.moveToNext()
            }
        }
        return news
    }

    companion object {
        // En caso de modificar la estructura de la BD, incrementar la version en 1.
        val DATABASE_VERSION = 3
        val DATABASE_NAME = "bdd.db"

        private val SQL_CREATE_ENTRIES_PAC =
            "CREATE TABLE " + DBContract.userPaciente.TABLE_NAME + " (" +
                    DBContract.userPaciente.COLUMN_ID + " TEXT PRIMARY KEY," +
                    DBContract.userPaciente.COLUMN_NOM + " TEXT," +
                    DBContract.userPaciente.COLUMN_EMAIL + " TEXT," +
                    DBContract.userPaciente.COLUMN_PASS + " TEXT," +
                    DBContract.userPaciente.COLUMN_COND + " TEXT," +
                    DBContract.userPaciente.COLUMN_ENFERM + " TEXT," +
                    DBContract.userPaciente.COLUMN_TELEFONO + " TEXT," +
                    DBContract.userPaciente.COLUMN_EDAD + " INTEGER)"

        private val SQL_CREATE_ENTRIES_DOC =
            "CREATE TABLE " + DBContract.userDoctor.TABLE_NAME + " (" +
                    DBContract.userDoctor.COLUMN_CEDULA + " TEXT PRIMARY KEY," +
                    DBContract.userDoctor.COLUMN_NOM + " TEXT," +
                    DBContract.userDoctor.COLUMN_ESPEC + " TEXT," +
                    DBContract.userDoctor.COLUMN_DOMICILIO + " TEXT," +
                    DBContract.userDoctor.COLUMN_TELEFONO + " TEXT," +
                    DBContract.userDoctor.COLUMN_EMAIL + " TEXT," +
                    DBContract.userDoctor.COLUMN_PASS + " TEXT," +
                    DBContract.userDoctor.COLUMN_DISP + " TEXT)"

        private val SQL_CREATE_PUBLIC =
            "CREATE TABLE " + DBContract.userPubli.TABLE_NAME + " (" +
                    DBContract.userPubli.COLUMN_TITULO + " TEXT PRIMARY KEY," +
                    DBContract.userPubli.COLUMN_TEMA + " TEXT," +
                    DBContract.userPubli.COLUMN_CONT + " TEXT," +
                    DBContract.userPubli.COLUMN_URLIMG + " TEXT)"


        private val SQL_DELETE_PUBLIC = "DROP TABLE IF EXISTS " + DBContract.userPubli.TABLE_NAME;
        private val SQL_DELETE_ENTRIES_DOC = "DROP TABLE IF EXISTS " + DBContract.userDoctor.TABLE_NAME;
        private val SQL_DELETE_ENTRIES_PAC = "DROP TABLE IF EXISTS " + DBContract.userPaciente.TABLE_NAME;
    }
}
