package com.example.trabajo_fin_grado.clases

import android.os.Parcel
import android.os.Parcelable

class Objetivo(
    private var cantidad: Double,
    private var descripcion: String,
    private var ahorrado: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readString()!!,
        parcel.readDouble()
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeDouble(cantidad)
        dest.writeString(descripcion)
        dest.writeDouble(ahorrado)
    }

    companion object CREATOR : Parcelable.Creator<Objetivo> {
        override fun createFromParcel(parcel: Parcel): Objetivo {
            return Objetivo(parcel)
        }

        override fun newArray(size: Int): Array<Objetivo?> {
            return arrayOfNulls(size)
        }
    }

}