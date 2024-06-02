package com.example.trabajo_fin_grado.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trabajo_fin_grado.R
import com.example.trabajo_fin_grado.clases.Usuario

class RegistroOperacionActivity : AppCompatActivity() {
    private lateinit var usuario: Usuario
    private lateinit var ingresos: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_operacion)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        usuario = intent.getParcelableExtra("Persona")!!
        val spinner: Spinner = findViewById(R.id.spinner)
        ingresos = findViewById(R.id.ingresos)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.dropdown_items,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // Acción a realizar cuando se selecciona un ítem
                val selectedItem = parent?.getItemAtPosition(position).toString()
                Toast.makeText(applicationContext, "Seleccionado: $selectedItem", Toast.LENGTH_LONG)
                    .show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Acción a realizar cuando no se selecciona ningún ítem
            }
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
        val intent = Intent(this@RegistroOperacionActivity, PerfilActivity::class.java)
        intent.putExtra("Persona", usuario)
        startActivity(intent)
    }

    private fun inicio() {
        val intent = Intent(this@RegistroOperacionActivity, DashboardActivity::class.java)
        intent.putExtra("Persona", usuario)
        startActivity(intent)
    }

    private fun operaciones() {
        val intent = Intent(this@RegistroOperacionActivity, RegistroOperacionActivity::class.java)
        intent.putExtra("Persona", usuario)
        startActivity(intent)
    }
}