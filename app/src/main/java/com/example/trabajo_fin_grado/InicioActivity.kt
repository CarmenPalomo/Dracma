package com.example.trabajo_fin_grado





import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast


class InicioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loging)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_desplegable,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item1->{
                Toast.makeText(this, "Perfil", Toast.LENGTH_SHORT).show();
            }
            R.id.item2->{
                Toast.makeText(this, "Opción 2", Toast.LENGTH_SHORT).show();
            }
            R.id.item3->{
                Toast.makeText(this, "Opción 3", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
