package com.example.trabajo_fin_grado;

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class LogingActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var usuariosRef: DatabaseReference

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
        setContentView(R.layout.activity_loging)

        auth = Firebase.auth
        database = FirebaseDatabase.getInstance()
        usuariosRef = database.getReference("usuarios")

        correo = findViewById(R.id.Correo)
        contraseña = findViewById(R.id.ContraseñaLog)
        nombre = findViewById(R.id.Nombre)
        apellido = findViewById(R.id.Apellido)
        crearCuenta = findViewById(R.id.BotonCrearCuent)
        botonAtras = findViewById(R.id.atras)

        var imagenPerfil: ImageView = findViewById(R.id.imagePerfil1)
        val botonSiguiente: Button = findViewById(R.id.botonSiguiente)

        imagenPerfil.setImageResource(id[indice])
        botonSiguiente.setOnClickListener {
            indice = (indice + 1) % id.size
            idImagenSeleccionada = id[indice]
            imagenPerfil.setImageResource(id[indice])
        }

        crearCuenta.setOnClickListener {
            if (correo.text.isNotEmpty() && contraseña.text.isNotEmpty() && nombre.text.isNotEmpty() && apellido.text.isNotEmpty()) {
                auth.createUserWithEmailAndPassword(
                    correo.text.toString(),
                    contraseña.text.toString()
                ).addOnCompleteListener {

                    if (contraseña.text.length <= 5) {
                        val builder = AlertDialog.Builder(this)
                        builder.setTitle("Error")
                        builder.setMessage("La contraseña no cumple con la complejidad")
                        builder.setPositiveButton("Aceptar", null)
                        val dialog: AlertDialog = builder.create()
                        dialog.show()
                    } else {
                        if (correo.text.contains("@") && correo.text.contains(".")) {

                            if (it.isSuccessful) {
                                datosUsuario(
                                    correo.text.toString(),
                                    nombre.text.toString(),
                                    apellido.text.toString(),
                                    idImagenSeleccionada
                                )
                                val usuario = Usuario(
                                    correo.text.toString(),
                                    nombre.text.toString(),
                                    apellido.text.toString(),
                                    idImagenSeleccionada
                                )
                                val registrado = Intent(this, MainActivity::class.java)
                                registrado.putExtra("Persona", usuario)
                                startActivity(registrado)
                            } else {
                                showAlert("Error en la autentificacion")
                            }
                        }

                    }
                }
            }
        }


        botonAtras.setOnClickListener {

            val registrarse = Intent(this, MainActivity::class.java)
            startActivity(registrarse)
        }


    }

    private fun showAlert(mensaje: String) {
        //Log.d(TAG, "Error creando nuevo usuario")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error en la creacion del usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun datosUsuario(
        correo: String,
        nombre: String,
        apellido: String,
        idImagenSeleccionada: Int
    ) {
        val usuarioActual: FirebaseUser? = auth.currentUser
        if (usuarioActual != null) {
            // insertamos los datos del usuario actual en nuestra Base de Datos
            val user = Usuario(correo, nombre, apellido, idImagenSeleccionada)
            usuariosRef.child(usuarioActual.uid).setValue(user)
        }
    }
}


