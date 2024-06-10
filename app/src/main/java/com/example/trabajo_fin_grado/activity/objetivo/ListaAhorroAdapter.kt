package com.example.trabajo_fin_grado.activity.objetivo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajo_fin_grado.R
import com.example.trabajo_fin_grado.clases.Objetivo
import com.example.trabajo_fin_grado.clases.Usuario

class ListaAhorroAdapter(
    private val objetivos: List<Objetivo>,
    private val usuario: Usuario) :
    RecyclerView.Adapter<ListaAhorroViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListaAhorroViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.elemento_lista_ahorro, viewGroup, false)
        val viewHolder = ListaAhorroViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ListaAhorroViewHolder, position: Int) {
        holder.datos(objetivos[position], usuario)
    }

    override fun getItemCount(): Int = objetivos.size
}