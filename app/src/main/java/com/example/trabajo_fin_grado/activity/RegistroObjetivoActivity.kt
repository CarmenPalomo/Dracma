package com.example.trabajo_fin_grado.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trabajo_fin_grado.R
import com.example.trabajo_fin_grado.clases.Objetivo
import com.example.trabajo_fin_grado.clases.Usuario
import com.example.trabajo_fin_grado.db.ObjetivoDatabase

class RegistroObjetivoActivity : AppCompatActivity() {

    private lateinit var usuario: Usuario
    private lateinit var cantidad: EditText
    private lateinit var descripcion: EditText
    private lateinit var ahorrado: EditText
    private lateinit var dbHandler: ObjetivoDatabase
    private lateinit var botonGuardarObjetivo: Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_objetivo)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        usuario = intent.getParcelableExtra("Usuario")!!
        dbHandler = ObjetivoDatabase(this)

        cantidad = findViewById(R.id.cantidad)
        descripcion = findViewById(R.id.descripcion)
        ahorrado = findViewById(R.id.ahorrado)
        botonGuardarObjetivo = findViewById(R.id.botonGuardarObjetivo)


        botonGuardarObjetivo.setOnClickListener {
            saveOperacion()
            listaAhorro()
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
            R.id.item4 ->{
                Toast.makeText(this, "Lista de ahorros", Toast.LENGTH_SHORT).show();
                listaAhorro()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun iniciarPerfil() {
        val intent = Intent(this@RegistroObjetivoActivity, PerfilActivity::class.java)
        intent.putExtra("Usuario", usuario)
        startActivity(intent)
    }

    private fun inicio() {
        val intent = Intent(this@RegistroObjetivoActivity, DashboardActivity::class.java)
        intent.putExtra("Usuario", usuario)
        startActivity(intent)
    }

    private fun operaciones() {
        val intent = Intent(this@RegistroObjetivoActivity, RegistroOperacionActivity::class.java)
        intent.putExtra("Usuario", usuario)
        startActivity(intent)
    }

    private fun listaAhorro() {
        val intent = Intent(this@RegistroObjetivoActivity, ObjetivosActivity::class.java)
        intent.putExtra("Usuario", usuario)
        startActivity(intent)
    }

    private fun saveOperacion() {
        val cantidad = cantidad.text.toString().toDouble()
        val descripcion = descripcion.text.toString()
        val ahorrado = ahorrado.text.toString().toDouble()

        val objetivo = Objetivo(cantidad, descripcion, ahorrado)
        dbHandler.addObjetivo(objetivo, usuario.getId())
        Toast.makeText(this, "Operación guardada", Toast.LENGTH_SHORT).show()
    }

}