package Practica_tema1.IESVDC.SegdoDAM

import android.content.ActivityNotFoundException
import android.content.Intent
import android.icu.util.Calendar
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.Settings
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

// --- MainActivity: La Pantalla Principal ---
// Esta es la primera pantalla que se abre. Su función es ser un menú principal
// con botones que llevan a las diferentes funciones de la aplicación.
class MainActivity : AppCompatActivity() {

    // La función onCreate se ejecuta una sola vez, cuando la pantalla se crea.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Carga el diseño visual definido en el archivo activity_main.xml.
        setContentView(R.layout.activity_main)

        // --- Configuración de los Botones ---

        // 1. Botón para ir a la pantalla de llamada de emergencia.
        val botonllamar = findViewById<ImageButton>(R.id.botonllamar)
        botonllamar.setOnClickListener {
            // Crea una "intención" para abrir la pantalla 'llamada'.
            val intent = Intent(this, llamada::class.java)
            // Inicia la nueva pantalla.
            startActivity(intent)
        }

        // 2. Botón para ir a la pantalla de Ajustes de la app.
        val botonAjustes = findViewById<ImageButton>(R.id.botonAjustes)
        botonAjustes.setOnClickListener {
            // Crea una "intención" para abrir la pantalla 'ConfActivity'.
            val intent = Intent(this, ConfActivity::class.java)
            startActivity(intent)
        }

        // 3. Botón para abrir una página web.
        val botonweb = findViewById<ImageButton>(R.id.botonweb)
        botonweb.setOnClickListener {
            // Busca en las preferencias si hay una URL guardada por el usuario.
            val sharedPreferences = getSharedPreferences("url", MODE_PRIVATE)
            val url = sharedPreferences.getString(
                "url",
                // Si no hay ninguna URL guardada, usa esta por defecto.
                "https://esupervivencia.com/wp-content/uploads/2012/05/curso-supervivencia-bosque.pdf"
            )

            // Si la URL no está vacía...
            if (!url.isNullOrEmpty()) {
                // Crea una "intención" para ver una URL en el navegador.
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                try {
                    // Intenta abrir el navegador.
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    // Si no hay un navegador instalado, muestra un aviso.
                    Toast.makeText(this, "No hay navegador disponible", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "No has introducido una URL válida", Toast.LENGTH_SHORT).show()
            }
        }

        // 4. Botón para abrir los ajustes de ahorro de batería del teléfono.
        val botonahorro = findViewById<ImageButton>(R.id.botonahorro)
        botonahorro.setOnClickListener {
            // Crea una "intención" para abrir una pantalla de ajustes específica del sistema.
            val intent = Intent(Settings.ACTION_BATTERY_SAVER_SETTINGS)
            startActivity(intent)
        }

        // 5. Botón para poner una alarma.
        val botonAlarma = findViewById<ImageButton>(R.id.alarma)
        botonAlarma.setOnClickListener {
            // Coge la hora actual.
            val now = Calendar.getInstance()
            // Le suma 2 minutos a la hora actual.
            now.add(Calendar.MINUTE, 2)

            // Crea una "intención" para que la app de reloj del móvil ponga una alarma.
            val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
                // Añade los datos de la alarma: mensaje, hora y minutos.
                putExtra(AlarmClock.EXTRA_MESSAGE, "Alarma automática 2 min")
                putExtra(AlarmClock.EXTRA_HOUR, now.get(Calendar.HOUR_OF_DAY))
                putExtra(AlarmClock.EXTRA_MINUTES, now.get(Calendar.MINUTE))
            }

            // Comprueba si hay alguna app de reloj que pueda gestionar la alarma.
            if (intent.resolveActivity(packageManager) != null) {
                // Si la hay, la inicia.
                startActivity(intent)
            } else {
                // Si no, muestra un aviso.
                Toast.makeText(this, "No se encontró app de reloj", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
