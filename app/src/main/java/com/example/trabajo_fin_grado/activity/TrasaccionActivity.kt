package com.example.trabajo_fin_grado.activity

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajo_fin_grado.R
import com.example.trabajo_fin_grado.clases.Operacion

class TransaccionesAdapter(private val listaOperaciones: List<Operacion>) : RecyclerView.Adapter<TransaccionesAdapter.TransaccionViewHolder>() {

    class TransaccionViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.activity_trasaccion, parent, false)) {
        private var textoTipo: TextView? = itemView.findViewById(R.id.textoTipo)
        private var textoCantidad: TextView? = itemView.findViewById(R.id.textoCantidad)
        private var textoDescripcion: TextView? = itemView.findViewById(R.id.textoDescripcion)
        private var textoCategoria: TextView? = itemView.findViewById(R.id.textoCategoria)

        fun bind(operacion: Operacion) {
            textoTipo?.text = operacion.getTipo().name
            textoCantidad?.text = operacion.getCantidad().toString()
            textoDescripcion?.text = operacion.getDescripcion()
            textoCategoria?.text = operacion.getCategoria().name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransaccionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TransaccionViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: TransaccionViewHolder, position: Int) {
        val operacion: Operacion = listaOperaciones[position]
        holder.bind(operacion)
    }

    override fun getItemCount(): Int = listaOperaciones.size
}
