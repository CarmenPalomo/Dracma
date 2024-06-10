package com.example.trabajo_fin_grado.activity.objetivo

import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajo_fin_grado.R
import com.example.trabajo_fin_grado.activity.DatosObjetivoActivity
import com.example.trabajo_fin_grado.clases.Objetivo
import com.example.trabajo_fin_grado.clases.Usuario

class ListaAhorroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private lateinit var objetivo: Objetivo
    private lateinit var usuario: Usuario

    init {
        itemView.findViewById<Button>(R.id.botonVerObjetivo).setOnClickListener {
            val intent = Intent(itemView.context, DatosObjetivoActivity::class.java)
            intent.putExtra("Objetivo", objetivo)
            intent.putExtra("Usuario", usuario)
            itemView.context.startActivity(intent)
        }
    }

    fun datos(objetivo: Objetivo, usuario: Usuario) {
        this.objetivo = objetivo
        this.usuario = usuario
        itemView.findViewById<TextView>(R.id.textoObjetivo).text = objetivo.getDescripcion()
    }

}