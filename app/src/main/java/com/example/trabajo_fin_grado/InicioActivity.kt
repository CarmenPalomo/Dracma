package com.example.trabajo_fin_grado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.TextView


class InicioActivity : AppCompatActivity() {

    private lateinit var usuario: Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        val imagenPeli: ImageView = findViewById(R.id.imagePeli)
        val imagenTele : ImageView = findViewById(R.id.imageTele)
        val imagenPerfil : ImageView = findViewById(R.id.imagenPerfil4)
        val scrollDestacados : HorizontalScrollView = findViewById(R.id.scrollDestacados)
        val scrollPeliculas : HorizontalScrollView = findViewById(R.id.scrollPeliculas)
        val scrollSeries : HorizontalScrollView = findViewById(R.id.scrollSeries)
        val textoDestacado : TextView = findViewById(R.id.textoDestacado)
        val textPeliculas : TextView = findViewById(R.id.textPeliculas)
        val textSerie : TextView = findViewById(R.id.textSerie)
        val imagen1Destacado : ImageView = findViewById(R.id.imagen1Destacado)
        val imagen2Destacado : ImageView = findViewById(R.id.imagen2Destacado)
        val imagen3Destacado : ImageView = findViewById(R.id.imagen3Destacado)
        val imagen4Destacado : ImageView = findViewById(R.id.imagen4Destacado)
        val imagen5Destacado : ImageView = findViewById(R.id.imagen5Destacado)
        val imagen1Peliculas : ImageView = findViewById(R.id.imagen1Peliculas)
        val imagen2Peliculas : ImageView = findViewById(R.id.imagen2Peliculas)
        val imagen3Peliculas : ImageView = findViewById(R.id.imagen3Peliculas)
        val imagen4Peliculas : ImageView = findViewById(R.id.imagen4Peliculas)
        val imagen5Peliculas : ImageView = findViewById(R.id.imagen5Peliculas)
        val imagen1Serie : ImageView = findViewById(R.id.imagen1Serie)
        val imagen2Serie : ImageView = findViewById(R.id.imagen2Serie)
        val imagen3Serie : ImageView = findViewById(R.id.imagen3Serie)
        val imagen4Serie : ImageView = findViewById(R.id.imagen4Serie)
        val imagen5Serie : ImageView = findViewById(R.id.imagen5Serie)

        usuario = intent.getParcelableExtra("Persona")!!

        imagenPerfil.setImageResource(usuario.getImagen())

        textoDestacado.visibility = View.VISIBLE
        scrollDestacados.visibility = View.VISIBLE

        textPeliculas.visibility = View.INVISIBLE
        textSerie.visibility = View.INVISIBLE
        scrollPeliculas.visibility = View.INVISIBLE
        scrollSeries.visibility = View.INVISIBLE





        imagen1Destacado.setOnClickListener {
            val inicio = Intent(this, Imagen1DestacadoActivity::class.java)
            inicio.putExtra("Persona",  usuario)
            startActivity(inicio)
        }

        imagen2Destacado.setOnClickListener {
            val inicio = Intent(this, Imagen2DestacadoActivity::class.java)
            inicio.putExtra("Persona",  usuario)
            startActivity(inicio)
        }

        imagen3Destacado.setOnClickListener {
            val inicio = Intent(this, Imagen3DestacadoActivity::class.java)
            inicio.putExtra("Persona",  usuario)
            startActivity(inicio)
        }

        imagen4Destacado.setOnClickListener {
            val inicio = Intent(this, Imagen4DestacadoActivity::class.java)
            inicio.putExtra("Persona",  usuario)
            startActivity(inicio)
        }

        imagen5Destacado.setOnClickListener {
            val inicio = Intent(this, Imagen5DestacadoActivity::class.java)
            inicio.putExtra("Persona",  usuario)
            startActivity(inicio)
        }

        imagen1Peliculas.setOnClickListener {
            val inicio = Intent(this, Pelicula1Activity::class.java)
            inicio.putExtra("Persona",  usuario)
            startActivity(inicio)
        }

        imagen2Peliculas.setOnClickListener {
            val inicio = Intent(this, Pelicula2Activity::class.java)
            inicio.putExtra("Persona",  usuario)
            startActivity(inicio)
        }

        imagen3Peliculas.setOnClickListener {
            val inicio = Intent(this, Pelicula3Activity::class.java)
            inicio.putExtra("Persona",  usuario)
            startActivity(inicio)
        }

        imagen4Peliculas.setOnClickListener {
            val inicio = Intent(this, Pelicula4Activity::class.java)
            inicio.putExtra("Persona",  usuario)
            startActivity(inicio)
        }

        imagen5Peliculas.setOnClickListener {
            val inicio = Intent(this, Pelicula5Activity::class.java)
            inicio.putExtra("Persona",  usuario)
            startActivity(inicio)
        }

        imagen1Serie.setOnClickListener {
            val inicio = Intent(this, Serie1Activity::class.java)
            inicio.putExtra("Persona",  usuario)
            startActivity(inicio)
        }

        imagen2Serie.setOnClickListener {
            val inicio = Intent(this, Serie2Activity::class.java)
            inicio.putExtra("Persona",  usuario)
            startActivity(inicio)
        }

        imagen3Serie.setOnClickListener {
            val inicio = Intent(this, Serie3Activity::class.java)
            inicio.putExtra("Persona",  usuario)
            startActivity(inicio)
        }

        imagen4Serie.setOnClickListener {
            val inicio = Intent(this, Serie4Activity::class.java)
            inicio.putExtra("Persona",  usuario)
            startActivity(inicio)
        }

        imagen5Serie.setOnClickListener {
            val inicio = Intent(this, Serie5Activity::class.java)
            inicio.putExtra("Persona",  usuario)
            startActivity(inicio)
        }

        imagenPeli.setOnClickListener {

            textPeliculas.visibility = View.VISIBLE
            scrollPeliculas.visibility = View.VISIBLE

            textoDestacado.visibility = View.INVISIBLE
            textSerie.visibility = View.INVISIBLE
            scrollDestacados.visibility = View.INVISIBLE
            scrollSeries.visibility = View.INVISIBLE


        }

        imagenTele.setOnClickListener {

            scrollSeries.visibility = View.VISIBLE
            textSerie.visibility = View.VISIBLE


            textPeliculas.visibility = View.INVISIBLE
            textoDestacado.visibility = View.INVISIBLE
            scrollDestacados.visibility = View.INVISIBLE
            scrollPeliculas.visibility = View.INVISIBLE


        }

        imagenPerfil.setOnClickListener {

            val perfil = Intent(this, PerfilActivity::class.java)
            perfil.putExtra("Persona",  usuario)

            startActivity(perfil)

        }

    }
}