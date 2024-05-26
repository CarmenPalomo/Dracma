package com.example.trabajo_fin_grado


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class InicioActivity : AppCompatActivity() {

    private lateinit var usuario: Usuario
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        usuario = intent.getParcelableExtra("Persona")!!
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
                Toast.makeText(this, "Opción 2", Toast.LENGTH_SHORT).show();
                operaciones()
            }

            R.id.item3 -> {
                Toast.makeText(this, "Inicio", Toast.LENGTH_SHORT).show();
                inicio()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun iniciarPerfil() {
        val intent = Intent(this@InicioActivity, PerfilActivity::class.java)
        intent.putExtra("Persona",  usuario)
        startActivity(intent)
    }

    private fun inicio() {
        val intent = Intent(this@InicioActivity, InicioActivity::class.java)
        intent.putExtra("Persona",  usuario)
        startActivity(intent)
    }

    private fun operaciones() {
        val intent = Intent(this@InicioActivity, OperacionActivity::class.java)
        intent.putExtra("Persona",  usuario)
        startActivity(intent)
    }
}

