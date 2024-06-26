package com.example.trabajo_fin_grado.activity


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajo_fin_grado.R
import com.example.trabajo_fin_grado.activity.operaciones.OperacionAdapter
import com.example.trabajo_fin_grado.clases.CategoriaOperacion
import com.example.trabajo_fin_grado.clases.Usuario
import com.example.trabajo_fin_grado.db.OperacionDatabase
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate


class DashboardActivity : AppCompatActivity() {

    private lateinit var usuario: Usuario
    private lateinit var pieChart: PieChart
    private lateinit var dbHandler: OperacionDatabase



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        usuario = intent.getParcelableExtra("Usuario")!!
        dbHandler = OperacionDatabase(this)
        pieChart = findViewById(R.id.pieChart)




        setupPieChart()
        loadPieChartData()
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewTransacciones)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = OperacionAdapter(dbHandler.getOperaciones(usuario.getId()))
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
        val intent = Intent(this@DashboardActivity, PerfilActivity::class.java)
        intent.putExtra("Usuario", usuario)
        startActivity(intent)
    }

    private fun inicio() {
        val intent = Intent(this@DashboardActivity, DashboardActivity::class.java)
        intent.putExtra("Usuario", usuario)
        startActivity(intent)
    }

    private fun operaciones() {
        val intent = Intent(this@DashboardActivity, RegistroOperacionActivity::class.java)
        intent.putExtra("Usuario", usuario)
        startActivity(intent)
    }

    private fun listaAhorro() {
        val intent = Intent(this@DashboardActivity, ObjetivosActivity::class.java)
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
        val listaOperaciones = dbHandler.getOperaciones(usuario.getId())
        val categoryMap = mutableMapOf<CategoriaOperacion, Double>()
        listaOperaciones.forEach {
            categoryMap[it.getCategoria()] = categoryMap.getOrDefault(it.getCategoria(), 0.0) + it.getCantidad()
        }

        val entries = ArrayList<PieEntry>()
        val colors = ArrayList<Int>()
        for ((categoria, total) in categoryMap) {
            entries.add(PieEntry(total.toFloat(), categoria.name))
            colors.add(ColorTemplate.MATERIAL_COLORS[categoryMap.keys.indexOf(categoria) % ColorTemplate.MATERIAL_COLORS.size])
        }

        val dataSet = PieDataSet(entries, "Categorías de Operaciones")
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

