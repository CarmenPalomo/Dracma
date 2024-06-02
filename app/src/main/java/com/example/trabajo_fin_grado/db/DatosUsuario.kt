package com.example.trabajo_fin_grado.db

class DatosUsuario(
    private var nombre: String,
    private var apellido: String,
    private var imagen: Int,
) {

    fun getNombre(): String {
        return nombre
    }

    fun getApellido(): String {
        return apellido
    }

    fun getImagen(): Int {
        return imagen
    }
}