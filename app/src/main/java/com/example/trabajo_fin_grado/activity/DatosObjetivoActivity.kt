package com.example.trabajo_fin_grado.activity

import android.content.Intent
import android.graphics.Color
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
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

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
        pieChart = findViewById(R.id.pieChart)

        setupPieChart()

        val botonGuardarAhorro = findViewById<Button>(R.id.botonGuardarAhorro)
        botonGuardarAhorro.setOnClickListener {
            val cantidadAhorrar = findViewById<EditText>(R.id.ahorro).text.toString().toDouble()     
            objetivo.setAhorrado(objetivo.getAhorrado() + cantidadAhorrar)
            dbObjetivo.updateObjetivo(objetivo, usuario.getId())

            usuario.getObjetivos()?.let { lista ->
                val index = lista.indexOf(objetivo)
                if (index != -1) {
                    lista[index] = objetivo
                }
                usuario.setObjetivos(lista)
            }

            loadPieChartData()

            Toast.makeText(this, "Ahorro guardado correctamente", Toast.LENGTH_SHORT).show()
        }

        dbHandler = OperacionDatabase(this)


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

    private fun setupPieChart() {
        pieChart.setUsePercentValues(true)
        pieChart.description.isEnabled = false
        pieChart.setExtraOffsets(5f, 10f, 5f, 5f)
        pieChart.dragDecelerationFrictionCoef = 0.95f
        pieChart.isDrawHoleEnabled = true
        pieChart.setHoleColor(Color.WHITE)
        pieChart.setTransparentCircleColor(Color.WHITE)
        pieChart.setTransparentCircleAlpha(110)
        pieChart.holeRadius = 58f
        pieChart.transparentCircleRadius = 61f
        pieChart.setDrawCenterText(true)
        pieChart.rotationAngle = 0f
        pieChart.isRotationEnabled = true
        pieChart.isHighlightPerTapEnabled = true
        pieChart.legend.orientation = Legend.LegendOrientation.VERTICAL
        pieChart.legend.isWordWrapEnabled = true
    }


    private fun loadPieChartData() {
        val total = objetivo.getCantidad()
        val ahorrado = objetivo.getAhorrado()
        val restante = total - ahorrado

        val entries = ArrayList<PieEntry>()
        entries.add(PieEntry(ahorrado.toFloat(), "Ahorrado"))
        entries.add(PieEntry(restante.toFloat(), "Restante"))

        val colors = ArrayList<Int>()
        colors.add(ColorTemplate.MATERIAL_COLORS[0]) // Color para "Ahorrado"
        colors.add(ColorTemplate.MATERIAL_COLORS[1]) // Color para "Restante"

        val dataSet = PieDataSet(entries, "Progreso del Objetivo")
        dataSet.colors = colors
        dataSet.sliceSpace = 3f
        dataSet.selectionShift = 5f
        dataSet.valueLinePart1OffsetPercentage = 80f
        dataSet.valueLinePart1Length = 0.2f
        dataSet.valueLinePart2Length = 0.4f
        dataSet.yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE

        val data = PieData(dataSet)
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.BLACK)

        pieChart.data = data
        pieChart.highlightValues(null)
        pieChart.invalidate()
    }
}