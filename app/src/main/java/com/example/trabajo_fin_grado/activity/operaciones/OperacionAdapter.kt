package com.example.trabajo_fin_grado.activity.operaciones

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajo_fin_grado.R
import com.example.trabajo_fin_grado.clases.Operacion

class OperacionAdapter(private val listaOperaciones: List<Operacion>) :
    RecyclerView.Adapter<OperacionViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): OperacionViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.elemento_operacion, viewGroup, false)
        return OperacionViewHolder(view)
    }

    override fun onBindViewHolder(holder: OperacionViewHolder, position: Int) {
        holder.guardar(listaOperaciones[position])
    }

    override fun getItemCount(): Int = listaOperaciones.size
}
