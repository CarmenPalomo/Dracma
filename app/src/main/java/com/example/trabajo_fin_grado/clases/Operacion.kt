package com.example.trabajo_fin_grado.clases

import android.os.Parcel
import android.os.Parcelable

class Operacion(
    var tipo: TipoOperacion,
    var cantidad: Double,
    var descripcion: String, //
    var categoria: CategoriaOperacion
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


    fun getTipo(): TipoOperacion {
        return tipo
    }

    fun getCantidad(): Double {
        return cantidad
    }

    fun getDescripcion(): String {
        return descripcion
    }

    fun getCategoria(): CategoriaOperacion {
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