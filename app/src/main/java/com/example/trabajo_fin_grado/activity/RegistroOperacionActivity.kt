package com.example.trabajo_fin_grado.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trabajo_fin_grado.R
import com.example.trabajo_fin_grado.clases.CategoriaOperacion
import com.example.trabajo_fin_grado.clases.Operacion
import com.example.trabajo_fin_grado.clases.TipoOperacion
import com.example.trabajo_fin_grado.clases.Usuario
import com.example.trabajo_fin_grado.db.OperacionesDatabase

class RegistroOperacionActivity : AppCompatActivity() {
    private lateinit var usuario: Usuario
    private lateinit var ingresos: EditText
    private lateinit var dbHandler: OperacionesDatabase
    private lateinit var spinnerOperacion: Spinner
    private lateinit var spinnerCategoria: Spinner
    private lateinit var botonIniciaSesion: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_operacion)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        usuario = intent.getParcelableExtra("Usuario")!!
        spinnerOperacion = findViewById(R.id.spinnerOperacion)
        spinnerCategoria = findViewById(R.id.spinnerCategoria)
        ingresos = findViewById(R.id.ingresos)
        dbHandler = OperacionesDatabase(this)
        botonIniciaSesion = findViewById(R.id.BotonIniciaSesion)


        val adapterOperacion = ArrayAdapter.createFromResource(
            this,
            R.array.string_operacion,
            android.R.layout.simple_spinner_item
        )

        val adapterCategoria = ArrayAdapter.createFromResource(
            this,
            R.array.string_categoria,
            android.R.layout.simple_spinner_item
        )


        adapterOperacion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerOperacion.adapter = adapterOperacion

        adapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategoria.adapter = adapterCategoria

        spinnerOperacion.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                Toast.makeText(applicationContext, "Seleccionado: $selectedItem", Toast.LENGTH_LONG)
                    .show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        spinnerCategoria.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                Toast.makeText(applicationContext, "Seleccionado: $selectedItem", Toast.LENGTH_LONG)
                    .show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        botonIniciaSesion.setOnClickListener {
            saveOperacion()
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
        intent.putExtra("Usuario", usuario)
        startActivity(intent)
    }

    private fun inicio() {
        val intent = Intent(this@RegistroOperacionActivity, DashboardActivity::class.java)
        intent.putExtra("Usuario", usuario)
        startActivity(intent)
    }

    private fun operaciones() {
        val intent = Intent(this@RegistroOperacionActivity, RegistroOperacionActivity::class.java)
        intent.putExtra("Usuario", usuario)
        startActivity(intent)
    }

    private fun saveOperacion() {
        val tipo = TipoOperacion.valueOf(spinnerOperacion.selectedItem.toString())
        val categoria = CategoriaOperacion.valueOf(spinnerCategoria.selectedItem.toString())
        val cantidad = ingresos.text.toString().toDouble()
        val descripcion = "Descripción genérica" // Puedes modificar esto según sea necesario

        val operacion = Operacion(tipo, cantidad, descripcion, categoria)
        dbHandler.addOperacion(operacion, usuario.getId())
        Toast.makeText(this, "Operación guardada", Toast.LENGTH_SHORT).show()
    }
}