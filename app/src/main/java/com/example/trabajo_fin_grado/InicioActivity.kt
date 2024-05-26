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
            }

            R.id.item3 -> {
                Toast.makeText(this, "Opción 3", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun iniciarPerfil() {
        val intent = Intent(this@InicioActivity, PerfilActivity::class.java)
        startActivity(intent)
    }
}

