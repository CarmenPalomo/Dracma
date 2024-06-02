package com.example.trabajo_fin_grado.activity;

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.trabajo_fin_grado.R
import com.example.trabajo_fin_grado.clases.Usuario
import com.example.trabajo_fin_grado.db.UsuarioDatabase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import java.util.logging.Logger

class RegistroActivity : AppCompatActivity() {
    private val log: Logger = Logger.getLogger("RegistroActivity")
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var dbHelper: UsuarioDatabase

    private lateinit var correo: EditText
    private lateinit var contraseña: EditText
    private lateinit var nombre: EditText
    private lateinit var apellido: EditText
    private lateinit var crearCuenta: Button
    private lateinit var botonAtras: Button
    private var idImagenSeleccionada: Int = 0

    private var indice = 0
    private var id = arrayOf(
        R.mipmap.perfil1, R.mipmap.perfil2, R.mipmap.perfil3,
        R.mipmap.perfil4, R.mipmap.perfil5, R.mipmap.perfil6,
        R.mipmap.perfil7, R.mipmap.perfil8, R.mipmap.perfil9,
        R.mipmap.perfil10
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        auth = Firebase.auth
        database = FirebaseDatabase.getInstance()
        dbHelper = UsuarioDatabase(this)

        correo = findViewById(R.id.Correo)
        contraseña = findViewById(R.id.ContraseñaLog)
        nombre = findViewById(R.id.Nombre)
        apellido = findViewById(R.id.Apellido)
        crearCuenta = findViewById(R.id.BotonCrearCuent)
        botonAtras = findViewById(R.id.atras)

        val imagenPerfil: ImageView = findViewById(R.id.imagePerfil1)
        val botonSiguiente: Button = findViewById(R.id.botonSiguiente)

        imagenPerfil.setImageResource(id[indice])
        botonSiguiente.setOnClickListener {
            indice = (indice + 1) % id.size
            idImagenSeleccionada = id[indice]
            imagenPerfil.setImageResource(id[indice])
        }

        crearCuenta.setOnClickListener {
            if (hayDatosUsuario()) {
                if (contraseña.text.length > 5) {
                    auth.createUserWithEmailAndPassword(
                        correo.text.toString(),
                        contraseña.text.toString()
                    )
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                val usuario = Usuario(
                                    auth.currentUser!!.uid,
                                    correo.text.toString(),
                                    nombre.text.toString(),
                                    apellido.text.toString(),
                                    idImagenSeleccionada,
                                    arrayListOf()
                                )
                                dbHelper.insertarUsuario(usuario)
                                log.info("usuario registrado")
                                val registrado = Intent(this, LoginActivity::class.java)
                                startActivity(registrado)
                            } else {
                                showAlert("Error en la autentificacion")
                            }
                        }
                } else {
                    showAlert("La contraseña no cumple con la complejidad")
                }
            }
        }

        botonAtras.setOnClickListener {
            val registrarse = Intent(this, LoginActivity::class.java)
            startActivity(registrarse)
        }


    }

    private fun hayDatosUsuario(): Boolean {
        return correo.text.isNotEmpty() && contraseña.text.isNotEmpty()
                && nombre.text.isNotEmpty() && apellido.text.isNotEmpty()
    }

    private fun showAlert(mensaje: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(mensaje)
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}

