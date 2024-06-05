package com.example.trabajo_fin_grado.clases

import android.os.Parcel
import android.os.Parcelable

class Operacion(
    private var tipo: TipoOperacion,
    private var cantidad: Double,
    private var descripcion: String, //
    private var categoria: CategoriaOperacion
) : Parcelable {

    constructor(parcel: Parcel) : this(
        TipoOperacion.valueOf(parcel.readString()!!),
        parcel.readDouble(),
        parcel.readString()!!,
        CategoriaOperacion.valueOf(parcel.readString()!!)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(tipo.name)
        parcel.writeDouble(cantidad)
        parcel.writeString(descripcion)
        parcel.writeString(categoria.name)
    }


    fun getipo(): TipoOperacion {
        return tipo
    }

    fun getcantidad(): Double {
        return cantidad
    }

    fun getdescripcion(): String {
        return descripcion
    }

    fun getcategoria(): CategoriaOperacion {
        return categoria
    }
    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "Operacion(tipo=$tipo, cantidad=$cantidad, descripcion=$descripcion, categoria=$categoria)"
    }

    companion object CREATOR : Parcelable.Creator<Operacion> {
        override fun createFromParcel(parcel: Parcel): Operacion {
            return Operacion(parcel)
        }

        override fun newArray(size: Int): Array<Operacion?> {
            return arrayOfNulls(size)
        }
    }
}