package com.example.trabajo_fin_grado.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajo_fin_grado.R
import com.example.trabajo_fin_grado.activity.objetivo.ListaAhorroAdapter
import com.example.trabajo_fin_grado.clases.Usuario
import com.example.trabajo_fin_grado.db.ObjetivoDatabase

class ObjetivosActivity : AppCompatActivity() {
    private lateinit var dbObjetivoHelper: ObjetivoDatabase
    private lateinit var usuario: Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_objetivos_ahorro)
        usuario = intent.getParcelableExtra("Usuario")!!

        dbObjetivoHelper = ObjetivoDatabase(this)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewTransacciones)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ListaAhorroAdapter(dbObjetivoHelper.getObjetivos(usuario.getId()))
    }
}