package com.example.trabajo_fin_grado.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trabajo_fin_grado.R
import com.example.trabajo_fin_grado.clases.Usuario
import java.util.logging.Logger

class PerfilActivity: AppCompatActivity() {

    private val log: Logger = Logger.getLogger("PerfilActivity")

    private lateinit var usuario: Usuario


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)
        setSupportActionBar(findViewById(R.id.my_toolbar))



        var textoNombre: TextView = findViewById(R.id.texoNombre)
        var textoApellido: TextView = findViewById(R.id.textoApellido)
        var textoEmail: TextView = findViewById(R.id.textoEmail)
        val imagenPerfil : ImageView = findViewById(R.id.imagePerfil)



        usuario = intent.getParcelableExtra("Usuario")!!
        log.info("persona obtenida :: $usuario")
        imagenPerfil.setImageResource(usuario.getImagen())

        textoNombre.text = "${usuario.getNombre()}"
        textoApellido.text = "${usuario.getApellido()}"
        textoEmail.text = "${usuario.getEmail()}"




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
            R.id.item4 ->{
                Toast.makeText(this, "Lista de ahorros", Toast.LENGTH_SHORT).show();
                listaAhorro()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun iniciarPerfil() {
        val intent = Intent(this@PerfilActivity, PerfilActivity::class.java)
        intent.putExtra("Usuario",  usuario)
        startActivity(intent)
    }

    private fun inicio() {
        val intent = Intent(this@PerfilActivity, DashboardActivity::class.java)
        intent.putExtra("Usuario",  usuario)
        startActivity(intent)
    }

    private fun operaciones() {
        val intent = Intent(this@PerfilActivity, RegistroOperacionActivity::class.java)
        intent.putExtra("Usuario",  usuario)
        startActivity(intent)
    }

    private fun listaAhorro() {
        val intent = Intent(this@PerfilActivity, ObjetivosActivity::class.java)
        intent.putExtra("Usuario", usuario)
        startActivity(intent)
    }
}





