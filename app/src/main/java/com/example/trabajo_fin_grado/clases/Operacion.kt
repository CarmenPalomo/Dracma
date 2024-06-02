package com.example.trabajo_fin_grado.clases

import android.os.Parcel
import android.os.Parcelable

class Operacion(
    private var tipo: String?,
    private var cantidad: Int,
    private var descripcion: String?,
    private var categoria: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(tipo)
        parcel.writeInt(cantidad)
        parcel.writeString(descripcion)
        parcel.writeString(categoria)
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