package com.example.trabajo_fin_grado

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {
    private  val log: Logger = Logger.getLogger("MainActivity")
    private lateinit var crearCuenta: Button
    private lateinit var iniciarSesion: Button
    private lateinit var  auth: FirebaseAuth 
    private lateinit var email: EditText
    private lateinit var contraseña: EditText
    private lateinit var usuario: Usuario
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

        val analytics : FirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message","Integracion de mensaje completada")
        analytics.logEvent("InitScreen", bundle)

        acceder()

    }

    private fun acceder(){
        crearCuenta = findViewById(R.id.botonRegistrarse)
        iniciarSesion = findViewById(R.id.BotonIniciaSesion)
        email = findViewById(R.id.correoElectronico)
        contraseña = findViewById(R.id.contraseña)
        val imagen = intent.getIntExtra("imagen", 0)
        log.info("Imagen obtenida. El id es : $imagen")
        iniciarSesion.setOnClickListener {
            if (email.text.isNotEmpty() && contraseña.text.isNotEmpty()){
                auth.signInWithEmailAndPassword(email.text.toString(),
                    contraseña.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful){
                        usuario = intent.getParcelableExtra("Persona")!!
                        val logged = Intent(this, InicioActivity::class.java)
                        logged.putExtra("Persona",  usuario)
                        startActivity(logged)
                    } else {
                        val error: String = when (it.exception) {
                            is FirebaseAuthInvalidUserException -> "El usuario no existe"
                            is FirebaseAuthEmailException -> "El correo no es valido"
                            is FirebaseAuthInvalidCredentialsException -> "La contraseña es incorrecta"
                            else -> "Error al autenticar"
                        }
                        Toast.makeText(this@MainActivity, error, Toast.LENGTH_LONG).show()

                    }
                }
            }else{
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Error")
                builder.setMessage("Se ha producido un error en la creacion del usuario")
                builder.setPositiveButton("Aceptar",null)
                val dialog: AlertDialog = builder.create()
                dialog.show()
            }
        }

        crearCuenta.setOnClickListener {
            val registrarse = Intent(this, LogingActivity::class.java)
            startActivity(registrarse)
        }
    }


}