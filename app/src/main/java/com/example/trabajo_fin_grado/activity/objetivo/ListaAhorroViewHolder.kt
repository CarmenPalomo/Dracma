package com.example.trabajo_fin_grado.activity.objetivo

import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajo_fin_grado.R
import com.example.trabajo_fin_grado.activity.DatosObjetivoActivity
import com.example.trabajo_fin_grado.clases.Objetivo

class ListaAhorroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private lateinit var objetivo: Objetivo

    init {
        itemView.findViewById<Button>(R.id.botonVerObjetivo).setOnClickListener {
            val intent = Intent(itemView.context, DatosObjetivoActivity::class.java)
            intent.putExtra("Objetivo", objetivo)
            itemView.context.startActivity(intent)
        }
    }

    fun datos(objetivo: Objetivo) {
        itemView.findViewById<TextView>(R.id.textoObjetivo).text = objetivo.getDescripcion()
    }

}