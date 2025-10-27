package Practica_tema1.IESVDC.SegdoDAM

import android.app.SearchManager
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.net.toUri
import android.provider.Settings

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val botonllamar = findViewById<ImageButton>(R.id.botonllamar)
        botonllamar.setOnClickListener {
            val intent = Intent(this, llamada::class.java)
            startActivity(intent)
        }

        val botonAjustes = findViewById<ImageButton>(R.id.botonAjustes)
        botonAjustes.setOnClickListener {
            val intent = Intent(this, ConfActivity::class.java)
            startActivity(intent)
        }

        val botonweb = findViewById<ImageButton>(R.id.botonweb)
        botonweb.setOnClickListener {
            val sharedPreferences = getSharedPreferences("url", MODE_PRIVATE)
            val url = sharedPreferences.getString(
                "url",
                "https://esupervivencia.com/wp-content/uploads/2012/05/curso-supervivencia-bosque.pdf"
            )

            if (!url.isNullOrEmpty()) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                try {
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(this, "No hay navegador disponible", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this, "No has introducido una URL válida", Toast.LENGTH_SHORT).show()
            }
        }
        //Este es el intent que yo he elegido, es un acceso a los ajustes sobre el ahorro de bateria
        val botonahorro = findViewById<ImageButton>(R.id.botonahorro)
        botonahorro.setOnClickListener {
            val intent = Intent(Settings.ACTION_BATTERY_SAVER_SETTINGS)
            startActivity(intent)
        }

    }
}