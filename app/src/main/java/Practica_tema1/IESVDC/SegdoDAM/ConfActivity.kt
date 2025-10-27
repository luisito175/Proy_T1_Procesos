package Practica_tema1.IESVDC.SegdoDAM
import Practica_tema1.IESVDC.SegdoDAM.databinding.ActivityConfBinding
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ConfActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Practica_tema1.IESVDC.SegdoDAM.databinding.ActivityConfBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        val sharedPreferences = getSharedPreferences("numero", MODE_PRIVATE)
        val numero = sharedPreferences.getString("numero", "")
        if (numero != null ){
            binding.editTextPhone.setText(numero)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.BotonGuardar.setOnClickListener {
            val numero = binding.editTextPhone.text.toString()
            val url = binding.editTextURL.text.toString()

            val sharedPreferences = getSharedPreferences("numero", MODE_PRIVATE)
            val sharedPreferences2 = getSharedPreferences("url", MODE_PRIVATE)

            val editor = sharedPreferences.edit()
            editor.putString("numero", numero)
            editor.apply()

            val editor2 = sharedPreferences2.edit()
            editor2.putString("url", url)
            editor2.apply()

            Toast.makeText(this, "Ajustes guardados correctamente", Toast.LENGTH_SHORT).show()
            finish()
        }


    }
}