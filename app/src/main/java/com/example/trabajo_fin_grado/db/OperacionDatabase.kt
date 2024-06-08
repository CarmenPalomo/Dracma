package com.example.trabajo_fin_grado.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.trabajo_fin_grado.clases.CategoriaOperacion
import com.example.trabajo_fin_grado.clases.Operacion
import com.example.trabajo_fin_grado.clases.TipoOperacion

class OperacionDatabase(context: Context) :
    SQLiteOpenHelper(context, DATABASE, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE = "OPERACIONES.db"
        private const val TABLA_OPERACIONES = "operacion"
        private const val COLUMNA_ID = "id"
        private const val COLUMNA_ID_USUARIO = "id_usuario"
        private const val COLUMNA_TIPO = "tipo"
        private const val COLUMNA_CANTIDAD = "cantidad"
        private const val COLUMNA_DESCRIPCION = "descripcion"
        private const val COLUMNA_CATEGORIA = "categoria"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_OPERACION = "CREATE TABLE $TABLA_OPERACIONES(" +
                "$COLUMNA_ID  INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMNA_ID_USUARIO TEXT, " +
                "$COLUMNA_TIPO TEXT, " +
                "$COLUMNA_CANTIDAD  INTEGER," +
                "$COLUMNA_DESCRIPCION TEXT," +
                "$COLUMNA_CATEGORIA  TEXT)"
        if (db != null) {
            db.execSQL(CREATE_TABLE_OPERACION)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db != null) {
            db.execSQL("DROP TABLE IF EXISTS $TABLA_OPERACIONES")
        }
        onCreate(db)
    }

    fun getOperacion(idUsuario: String): ArrayList<Operacion> {
        val selectQuery = "SELECT * FROM $TABLA_OPERACIONES WHERE $COLUMNA_ID_USUARIO = '$idUsuario'"
        val listaOperaciones = arrayListOf<Operacion>()
        val dbRead = this.readableDatabase
        val cursor = dbRead.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val tipo = TipoOperacion.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(COLUMNA_TIPO)))
                val cantidad = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMNA_CANTIDAD))
                val descripcion = cursor.getString(cursor.getColumnIndexOrThrow(COLUMNA_DESCRIPCION))
                val categoria = CategoriaOperacion.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(COLUMNA_CATEGORIA)))
                listaOperaciones.add(Operacion(tipo, cantidad, descripcion, categoria))
            } while (cursor.moveToNext())
        }
        cursor.close()
        dbRead.close()
        return listaOperaciones
    }

    fun addOperacion(operacion: Operacion, idUsuario: String) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMNA_ID_USUARIO, idUsuario)
            put(COLUMNA_TIPO, operacion.getTipo().name)
            put(COLUMNA_CANTIDAD, operacion.getCantidad())
            put(COLUMNA_DESCRIPCION, operacion.getDescripcion())
            put(COLUMNA_CATEGORIA, operacion.getCategoria().name)
        }
        db.insert(TABLA_OPERACIONES, null, values)
        db.close()
    }

}