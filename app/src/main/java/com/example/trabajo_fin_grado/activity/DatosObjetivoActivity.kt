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
import com.example.trabajo_fin_grado.db.OperacionDatabase
import com.github.mikephil.charting.charts.PieChart

class DatosObjetivoActivity : AppCompatActivity() {
    private lateinit var objetivo: Objetivo
    private lateinit var pieChart: PieChart
    private lateinit var dbHandler: OperacionDatabase
    private lateinit var usuario: Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ahorro)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        objetivo = intent.getParcelableExtra("Objetivo")!!
        usuario = intent.getParcelableExtra("Usuario")!!

        val botonGuardarAhorro = findViewById<Button>(R.id.botonGuardarAhorro)
        botonGuardarAhorro.setOnClickListener {
            val cantidadAhorrar = findViewById<EditText>(R.id.ahorro).text.toString().toDouble()
            //TODO: guardar el ahorro. actualizar el grafico. actualizar bbdd con el objetivo modificado
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