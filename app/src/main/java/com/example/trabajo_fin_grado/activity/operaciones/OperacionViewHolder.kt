package com.example.trabajo_fin_grado.activity.operaciones

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajo_fin_grado.R
import com.example.trabajo_fin_grado.clases.Operacion

class OperacionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun guardar(operacion: Operacion) {
        val textoTipo: TextView = itemView.findViewById(R.id.textoTipo)
        val textoCantidad: TextView = itemView.findViewById(R.id.textoCantidad)
        val textoDescripcion: TextView = itemView.findViewById(R.id.textoDescripcion)
        val textoCategoria: TextView = itemView.findViewById(R.id.textoCategoria)

        textoTipo.text = operacion.getTipo().name
        textoCantidad.text = operacion.getCantidad().toString()
        textoDescripcion.text = operacion.getDescripcion()
        textoCategoria.text = operacion.getCategoria().name
    }
}