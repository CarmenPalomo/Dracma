package com.example.trabajo_fin_grado.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.trabajo_fin_grado.clases.Objetivo

class ObjetivoDatabase(context: Context) :
    SQLiteOpenHelper(context, DATABASE, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE = "OBJETIVO.db"
        private const val TABLA_OBJETIVO = "OBJETIVO"
        private const val COLUMNA_ID = "id"
        private const val COLUMNA_ID_USUARIO = "id_usuario"
        private const val COLUMNA_CANTIDAD = "cantidad"
        private const val COLUMNA_DESCRIPCION = "descripcion"
        private const val COLUMNA_AHORRO = "ahorro"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_OBJETIVO = "CREATE TABLE $TABLA_OBJETIVO(" +
                "$COLUMNA_ID  INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMNA_ID_USUARIO TEXT, " +
                "$COLUMNA_CANTIDAD INTEGER " +
                "$COLUMNA_DESCRIPCION  TEXT," +
                "$COLUMNA_AHORRO INTEGER)"
        if (db != null) {
            db.execSQL(CREATE_TABLE_OBJETIVO)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db != null) {
            db.execSQL("DROP TABLE IF EXISTS $TABLA_OBJETIVO")
        }
        onCreate(db)
    }

    fun getObjetivos(idUsuario: String): ArrayList<Objetivo> {
        val selectQuery = "SELECT * FROM $TABLA_OBJETIVO WHERE $COLUMNA_ID_USUARIO = '$idUsuario'"
        val listaObjetivos = arrayListOf<Objetivo>()
        val dbRead = this.readableDatabase
        val cursor = dbRead.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val cantidad = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMNA_CANTIDAD))
                val descripcion = cursor.getString(cursor.getColumnIndexOrThrow(COLUMNA_DESCRIPCION))
                val ahorro = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMNA_AHORRO))
                listaObjetivos.add(Objetivo(cantidad, descripcion, ahorro))
            } while (cursor.moveToNext())
        }
        cursor.close()
        dbRead.close()
        return listaObjetivos
    }

    fun addObjetivo(objetivo: Objetivo, idUsuario: String) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMNA_ID_USUARIO, idUsuario)
            put(COLUMNA_CANTIDAD, objetivo.getCantidad())
            put(COLUMNA_DESCRIPCION, objetivo.getDescripcion())
            put(COLUMNA_AHORRO, objetivo.getAhorrado())
        }
        db.insert(TABLA_OBJETIVO, null, values)
        db.close()
    }
}