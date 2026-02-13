package com.example.jugandodadoskotlin

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.jugandodadoskotlin.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

lateinit var juego: Job
lateinit var miAdaptador: ArrayAdapter<String>
var arrayOpc1 = arrayOf("Par", "Impar")
var arrayOpc2 = arrayOf("Mayor/igual que 7", "Menor que 7")
class MainActivity : AppCompatActivity(), View.OnClickListener, (DialogInterface, Int) -> Unit {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) quitar modo oscuro
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.lanzar.setOnClickListener(this)

        binding.toggleButton.addOnButtonCheckedListener { group, i, bool ->
            if (bool) {
                when (i) {
                    binding.parImpar.id -> miAdaptador =
                        ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayOpc1)

                    binding.mm7.id -> miAdaptador =
                        ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayOpc2)
                }
                binding.spinner.adapter = miAdaptador
            } else if (group.checkedButtonId == -1) {
                binding.spinner.adapter = null
            }
        }

    }

    override fun onClick(v: View?) {
        //Toast.makeText(this, "Probando mensajes", Toast.LENGTH_LONG).show()
        if ((binding.toggleButton.checkedButtonId == -1) || binding.apuesta.text.toString().isEmpty()) {
            Snackbar.make(binding.clSb, "Debe elegir una opción de juego", Snackbar.LENGTH_SHORT).show()
        } else {
            val apuestaInt = binding.apuesta.text.toString().toInt()
            var textoSaldoInt = binding.textoSaldo.text.toString().toInt()

            if (apuestaInt > textoSaldoInt) {
                Snackbar.make(
                    binding.main,
                    "La apuesta no puede ser mayor al saldo disponible",
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                binding.textoSaldo.text = (textoSaldoInt - apuestaInt).toString()
                textoSaldoInt = binding.textoSaldo.text.toString().toInt()
                juego = lifecycleScope.launch {
                    var ganado = lanzarDados()

                    if (ganado) {
                        binding.imagen.setImageResource(R.drawable.ganar_dados)
                    } else {
                        binding.imagen.setImageResource(R.drawable.perder_dados)
                    }

                    var numApuesta: Int
                    if (ganado) {
                        numApuesta = apuestaInt * 2 + textoSaldoInt
                        binding.textoSaldo.text = numApuesta.toString()
                    }

                    reiniciarJuego()
                }
            }

        }
    }

    suspend fun lanzarDados(): Boolean {
        Glide.with(this).load(R.drawable.dado_imagen_animada_0092).into(binding.imagen)
        var num1 = (Math.random() * 6 + 1).toInt()
        var num2 = (Math.random() * 6 + 1).toInt()

        delay(3000L)

        binding.num1.text = num1.toString()
        binding.num2.text = num2.toString()
        var sumaDados = num1 + num2
        var opcionSelect = binding.spinner.selectedItem
        var ganado = false
        when (opcionSelect) {
            "Par" -> if (sumaDados % 2 == 0) {
                ganado = true
            }

            "Impar" -> if (sumaDados % 2 == 1) {
                ganado = true
            }

            "Mayor/igual que 7" -> if (sumaDados >= 7) {
                ganado = true
            }

            "Menor que 7" -> if (sumaDados < 7) {
                ganado = true
            }
        }

        return ganado


    }

    suspend fun reiniciarJuego() {
        var textoSaldoInt = binding.textoSaldo.text.toString().toInt()

        delay(1000)

        var alertaFinJuego = AlertDialog.Builder(this)
        alertaFinJuego.setTitle("Jugando a los dados")
        alertaFinJuego.setNegativeButton(
            "Salir del juego",
            DialogInterface.OnClickListener(this)
        )

        if (textoSaldoInt <= 0) {
            binding.imagen.setImageResource(R.drawable.bancarrota)
            alertaFinJuego.setMessage("Estas arruinado. Debes dejar el juego.")
        } else {
            alertaFinJuego.setMessage("¿Desea seguir jugando?")
            alertaFinJuego.setPositiveButton(
                "Seguir jugando",
                DialogInterface.OnClickListener(this)
            )
        }
        alertaFinJuego.show()
    }

    override fun invoke(p1: DialogInterface, p2: Int) {
        if (p2 == -2) {
            this.finish()
        }
    }

}