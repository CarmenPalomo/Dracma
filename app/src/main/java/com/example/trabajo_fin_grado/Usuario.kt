package com.example.trabajo_fin_grado;

import android.os.Parcel
import android.os.Parcelable

class Usuario(
    private var email: String?,
    private var nombre: String?,
    private var apellido: String?,
    private var imagen: Int,
    private var operaciones: ArrayList<Operacion>?
) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.createTypedArrayList(Operacion.CREATOR)
    ) {
    }

    fun getNombre(): String? {
        return nombre
    }

    fun getEmail(): String? {
        return email
    }

    fun getApellido(): String? {
        return apellido
    }

    fun getImagen(): Int {
        return imagen
    }

    fun setNombre(nombre: String?) {
        this.nombre = nombre
    }

    fun setApellido(nombre: String?) {
        this.apellido = apellido
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(email)
        parcel.writeString(nombre)
        parcel.writeString(apellido)
        parcel.writeInt(imagen)
        parcel.writeList(operaciones)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "Usuario(email=$email, nombre=$nombre, apellido=$apellido, imagen=$imagen)"
    }

    companion object CREATOR : Parcelable.Creator<Usuario> {
        override fun createFromParcel(parcel: Parcel): Usuario {
            return Usuario(parcel)
        }

        override fun newArray(size: Int): Array<Usuario?> {
            return arrayOfNulls(size)
        }
    }


}