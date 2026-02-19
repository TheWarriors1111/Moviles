package com.example.libre2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.libre2.databinding.ActivityMainBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var miCorrutina: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(view)
        //setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnSinHilos.setOnClickListener {
            binding.pb1.progress = 0
            binding.pb1.max = 100
            for (i in 1..10) {
                tarea()
                binding.pb1.progress = i * 10
            }
            binding.txt.text = "Tarea finalizada"
        }

        binding.btnHola.setOnClickListener {
            Toast.makeText(this, "Aprendiendo corrutinas", Toast.LENGTH_SHORT).show()
        }

        binding.btnConHilos.setOnClickListener {
            binding.pb1.progress = 0
            binding.pb1.max = 100
            miCorrutina = lifecycleScope.launch {
                for (i in 1..10) {
                    doSomethingUsefulOne()
                    binding.pb1.progress = i * 10
                }
                binding.txt.text = "Tarea finalizada"
            }
        }

        binding.btnCancelar.setOnClickListener {
            miCorrutina.cancel()
            binding.txt.text = "Tarea cancelada"
        }
    }

    fun tarea() {
        Thread.sleep(1000)
    }

    suspend fun doSomethingUsefulOne(): Int {
        delay(1000L) // pretend we are doing something useful here
        return 13
    }
}