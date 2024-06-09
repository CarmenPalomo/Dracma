package com.example.trabajo_fin_grado.clases;

import android.os.Parcel
import android.os.Parcelable

class Usuario(
    private var idUsuario: String,
    private var email: String,
    private var nombre: String,
    private var apellido: String,
    private var imagen: Int,
    private var operaciones: ArrayList<Operacion>?,
    private var objetivos: ArrayList<Objetivo>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.createTypedArrayList(Operacion),
        parcel.createTypedArrayList(Objetivo)
    )

    fun getId(): String {
        return idUsuario
    }

    fun getNombre(): String {
        return nombre
    }

    fun getEmail(): String {
        return email
    }

    fun getApellido(): String {
        return apellido
    }

    fun getImagen(): Int {
        return imagen
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idUsuario)
        parcel.writeString(email)
        parcel.writeString(nombre)
        parcel.writeString(apellido)
        parcel.writeInt(imagen)
        parcel.writeTypedList(operaciones)
        parcel.writeTypedList(objetivos)
    }


    override fun toString(): String {
        return "Usuario(idUsuario='$idUsuario', email='$email', nombre='$nombre', apellido='$apellido', imagen=$imagen, operaciones=$operaciones)"
    }

    override fun describeContents(): Int {
        return 0
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