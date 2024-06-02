package com.example.trabajo_fin_grado.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.trabajo_fin_grado.clases.Usuario

class UsuarioDatabase(context: Context) :
    SQLiteOpenHelper(context, DATABASE, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE = "USUARIO.db"
        private const val TABLA_USUARIO = "USUARIO"
        private const val COLUMNA_ID = "id"
        private const val COLUMNA_NOMBRE = "nombre"
        private const val COLUMNA_APELLIDO = "apellido"
        private const val COLUMNA_IMAGEN = "imagen"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_USERS_TABLE = "CREATE TABLE $TABLA_USUARIO(" +
                "$COLUMNA_ID TEXT PRIMARY KEY, " +
                "$COLUMNA_NOMBRE TEXT, " +
                "$COLUMNA_APELLIDO TEXT, " +
                "$COLUMNA_IMAGEN INTEGER)"
        if (db != null) {
            db.execSQL(CREATE_USERS_TABLE)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db != null) {
            db.execSQL("DROP TABLE IF EXISTS $TABLA_USUARIO")
        }
        onCreate(db)
    }

    fun insertarUsuario(usuario: Usuario): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMNA_NOMBRE, usuario.getNombre())
            put(COLUMNA_APELLIDO, usuario.getApellido())
            put(COLUMNA_IMAGEN, usuario.getImagen())
            put(COLUMNA_ID, usuario.getId())
        }
        return db.insert(TABLA_USUARIO, null, values)
    }

    fun obtenerUsuario(idUsuario: String): DatosUsuario {
        val dbRead = this.readableDatabase
        val usuarioQuery = "SELECT * FROM $TABLA_USUARIO WHERE $COLUMNA_ID = '$idUsuario'"
        val cursor = dbRead.rawQuery(usuarioQuery, null)
        var aDevolver = DatosUsuario("", "", 0)

        if (cursor.moveToFirst()) {
            val nombre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMNA_NOMBRE))
            val apellido = cursor.getString(cursor.getColumnIndexOrThrow(COLUMNA_APELLIDO))
            val imagen = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMNA_IMAGEN))
            aDevolver = DatosUsuario(nombre, apellido, imagen)
        }
        dbRead.close()
        cursor.close()
        return aDevolver
    }
}