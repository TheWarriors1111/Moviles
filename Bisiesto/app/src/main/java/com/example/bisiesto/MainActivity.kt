package com.example.bisiesto

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var numAlea: TextView
    lateinit var respuesta: TextView
    lateinit var si: RadioButton
    lateinit var no: RadioButton
    lateinit var fondo: Switch
    lateinit var princ: ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        numAlea = findViewById<TextView>(R.id.textViewNumAlea)
        respuesta = findViewById<TextView>(R.id.textViewRes)
        si = findViewById<RadioButton>(R.id.radioButtonSi)
        no = findViewById<RadioButton>(R.id.radioButtonNo)
        fondo = findViewById<Switch>(R.id.switchFondo)
        princ = findViewById<ConstraintLayout>(R.id.main)
    }

    fun generarAlea(v: View) {
        numAlea.text = (Math.random() * 601 + 1900).toInt().toString()
    }

    fun comprobarBisiesto(v: View) {
        var anio = Integer.parseInt(numAlea.text.toString())
        var acierto = 'Y'

        if (anio % 4 == 0) {
            if (anio % 100 == 0) {
                if (anio % 400 == 0) {
                    acierto = 'Y'
                } else {
                    acierto = 'N'
                }
            } else {
                acierto = 'Y'
            }
        } else {
            acierto = 'N'
        }

        var colorTexto: Int = 0
        if (!si.isChecked && !no.isChecked) {
            respuesta.text = "Debe escoger una de las opciones"
            colorTexto = Color.BLUE
        } else {
            var aciertoCompr: String? = null
            when (acierto) {
                'Y' -> if (si.isChecked) {
                    aciertoCompr = "Acertaste"
                    colorTexto = Color.GREEN
                } else {
                    aciertoCompr = "Error"
                    colorTexto = Color.RED
                }

                'N' -> if (no.isChecked) {
                    aciertoCompr = "Acertaste"
                    colorTexto = Color.GREEN
                } else {
                    aciertoCompr = "Error"
                    colorTexto = Color.RED
                }
            }
            respuesta.text = aciertoCompr
        }
        respuesta.setTextColor(colorTexto)
    }

    fun cambiarFondo(v: View) {
        if (fondo.isChecked) {
            princ.setBackgroundColor(Color.YELLOW)
        } else {
            princ.setBackgroundColor(Color.WHITE)
        }
    }
}