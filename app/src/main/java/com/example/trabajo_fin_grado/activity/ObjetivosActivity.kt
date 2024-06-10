package com.example.trabajo_fin_grado.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
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
    private lateinit var botonAniadir: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_objetivos_ahorro)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        usuario = intent.getParcelableExtra("Usuario")!!
        botonAniadir = findViewById(R.id.botonAniadir)

        dbObjetivoHelper = ObjetivoDatabase(this)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewTransacciones)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ListaAhorroAdapter(
            dbObjetivoHelper.getObjetivos(usuario.getId()),
            usuario
        )

        botonAniadir.setOnClickListener {
            val intent = Intent(this@ObjetivosActivity, RegistroObjetivoActivity::class.java)
            intent.putExtra("Usuario", usuario)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_desplegable, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item1 -> {
                Toast.makeText(this, "Perfil", Toast.LENGTH_SHORT).show();
                iniciarPerfil()
            }

            R.id.item2 -> {
                Toast.makeText(this, "Operacion", Toast.LENGTH_SHORT).show();
                operaciones()
            }

            R.id.item3 -> {
                Toast.makeText(this, "Inicio", Toast.LENGTH_SHORT).show();
                inicio()
            }

            R.id.item4 -> {
                Toast.makeText(this, "Lista de ahorros", Toast.LENGTH_SHORT).show();
                listaAhorro()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun iniciarPerfil() {
        val intent = Intent(this@ObjetivosActivity, PerfilActivity::class.java)
        intent.putExtra("Usuario", usuario)
        startActivity(intent)
    }

    private fun inicio() {
        val intent = Intent(this@ObjetivosActivity, DashboardActivity::class.java)
        intent.putExtra("Usuario", usuario)
        startActivity(intent)
    }

    private fun operaciones() {
        val intent = Intent(this@ObjetivosActivity, RegistroOperacionActivity::class.java)
        intent.putExtra("Usuario", usuario)
        startActivity(intent)
    }

    private fun listaAhorro() {
        val intent = Intent(this@ObjetivosActivity, ObjetivosActivity::class.java)
        intent.putExtra("Usuario", usuario)
        startActivity(intent)
    }
}