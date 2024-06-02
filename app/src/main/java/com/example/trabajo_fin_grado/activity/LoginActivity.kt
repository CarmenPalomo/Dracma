package com.example.trabajo_fin_grado.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.trabajo_fin_grado.R
import com.example.trabajo_fin_grado.clases.Usuario
import com.example.trabajo_fin_grado.db.OperacionesDatabase
import com.example.trabajo_fin_grado.db.UsuarioDatabase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.logging.Logger

class LoginActivity : AppCompatActivity() {
    private val log: Logger = Logger.getLogger("LoginActivity")

    private lateinit var dbUsuarioHelper: UsuarioDatabase
    private lateinit var dbOperacionesHelper: OperacionesDatabase

    private lateinit var crearCuenta: Button
    private lateinit var iniciarSesion: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var email: EditText
    private lateinit var contrasenia: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        dbOperacionesHelper = OperacionesDatabase(this)
        dbUsuarioHelper = UsuarioDatabase(this)
        auth = Firebase.auth
        acceder()

    }

    private fun acceder() {
        crearCuenta = findViewById(R.id.botonRegistrarse)
        iniciarSesion = findViewById(R.id.BotonIniciaSesion)
        email = findViewById(R.id.correoElectronico)
        contrasenia = findViewById(R.id.contraseña)

        iniciarSesion.setOnClickListener {
            if (email.text.isNotEmpty() && contrasenia.text.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email.text.toString(), contrasenia.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            log.info("usuario logado")
                            val userId = auth.currentUser!!.uid
                            log.info("obtenemos datos del usuario")
                            val datosUsuario = dbUsuarioHelper.obtenerUsuario(userId)
                            val operaciones = dbOperacionesHelper.getOperacion(userId)
                            val usuarioActual =
                                Usuario(
                                    userId,
                                    email.text.toString(),
                                    datosUsuario.getNombre(),
                                    datosUsuario.getApellido(),
                                    datosUsuario.getImagen(),
                                    operaciones
                                )
                            log.info("datos del usuario obtenidos")
                            val dashboardIntent = Intent(this, DashboardActivity::class.java)
                            dashboardIntent.putExtra("Usuario", usuarioActual)
                            startActivity(dashboardIntent)

                        } else {
                            val error: String = when (it.exception) {
                                is FirebaseAuthInvalidUserException -> "El usuario no existe"
                                is FirebaseAuthEmailException -> "El correo no es valido"
                                is FirebaseAuthInvalidCredentialsException -> "La contraseña es incorrecta"
                                else -> "Error al autenticar"
                            }
                            showAlert(error)
                        }
                    }
            } else {
                showAlert("email o contraseña vacios.")
            }
        }

        crearCuenta.setOnClickListener {
            val registrarse = Intent(this, RegistroActivity::class.java)
            startActivity(registrarse)
        }
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