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
import com.example.trabajo_fin_grado.db.OperacionDatabase
import com.github.mikephil.charting.charts.PieChart

class DatosObjetivoActivity : AppCompatActivity() {
    private lateinit var objetivo: Objetivo
    private lateinit var pieChart: PieChart
    private lateinit var dbHandler: OperacionDatabase
    private lateinit var dbObjetivo: ObjetivoDatabase
    private lateinit var usuario: Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ahorro)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        objetivo = intent.getParcelableExtra("Objetivo")!!
        usuario = intent.getParcelableExtra("Usuario")!!
        dbObjetivo = ObjetivoDatabase(this)

        val botonGuardarAhorro = findViewById<Button>(R.id.botonGuardarAhorro)
        botonGuardarAhorro.setOnClickListener {
            val cantidadAhorrar = findViewById<EditText>(R.id.ahorro).text.toString().toDouble()
            objetivo.setAhorrado(objetivo.getAhorrado() + cantidadAhorrar) // Asumiendo que quieres sumar a lo ya ahorrado

            // Actualizar en la base de datos
            dbObjetivo.addObjetivo(objetivo, usuario.getId())

            // Actualizar el gráfico de pie
            loadPieChartData(objetivo.getAhorrado().toInt())

            // Actualizar la lista de objetivos en el usuario si es necesario
            usuario.getObjetivos()?.let { lista ->
                val index = lista.indexOf(objetivo)
                if (index != -1) {
                    lista[index] = objetivo
                }
                usuario.setObjetivos(lista)  // Actualiza la lista completa en el objeto usuario
            }

            // Mostrar confirmación
            Toast.makeText(this, "Ahorro guardado correctamente", Toast.LENGTH_SHORT).show()
        }

        dbHandler = OperacionDatabase(this)
        pieChart = findViewById(R.id.pieChart)


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
        val intent = Intent(this@DatosObjetivoActivity, PerfilActivity::class.java)
        intent.putExtra("Usuario", usuario )
        startActivity(intent)
    }

    private fun inicio() {
        val intent = Intent(this@DatosObjetivoActivity, DashboardActivity::class.java)
        intent.putExtra("Usuario", usuario)
        startActivity(intent)
    }

    private fun operaciones() {
        val intent = Intent(this@DatosObjetivoActivity, RegistroOperacionActivity::class.java)
        intent.putExtra("Usuario", usuario)
        startActivity(intent)
    }
    private fun listaAhorro() {
        val intent = Intent(this@DatosObjetivoActivity, ObjetivosActivity::class.java)
        intent.putExtra("Usuario", usuario)
        startActivity(intent)
    }


    private fun loadPieChartData(ingreso: Int) {

    }
}