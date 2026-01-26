package com.example.divisible

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var numAlea: TextView
    lateinit var div2: Switch
    lateinit var div3: Switch
    lateinit var div5: Switch
    lateinit var div10: Switch
    lateinit var divNo: Switch
    lateinit var respuesta: TextView
    lateinit var imagen: ImageView
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
        div2 = findViewById<Switch>(R.id.switch2)
        div3 = findViewById<Switch>(R.id.switch3)
        div5 = findViewById<Switch>(R.id.switch5)
        div10 = findViewById<Switch>(R.id.switch10)
        divNo = findViewById<Switch>(R.id.switchNo)
        respuesta = findViewById<TextView>(R.id.textViewRes)
        imagen = findViewById<ImageView>(R.id.imageView)
    }

    fun generarAlea(v: View) {
        numAlea.text = (Math.random() * 1001 + 1000).toInt().toString()
    }

    fun comprobarDivisible(v: View) {
        var numero = Integer.parseInt(numAlea.text.toString())

        var imagenI = 0
        if (!div2.isChecked && !div3.isChecked && !div5.isChecked && !div10.isChecked && !divNo.isChecked) {
            respuesta.text = "Debes escoger al menos una de las opciones"
        } else {
            var aciertoComp: String? = null

            if (!divNo.isChecked) {
                if (div2.isChecked == (numero % 2 == 0) && div3.isChecked == (numero % 3 == 0) && div5.isChecked == (numero % 5 == 0) && div10.isChecked == (numero % 10 == 0)) {
                    aciertoComp = "Correcto"
                    imagenI = R.drawable.ok
                } else {
                    aciertoComp = "Error"
                    imagenI = R.drawable.ko
                }
            } else {

                if ((div2.isChecked == (numero % 2 == 0) && div3.isChecked == (numero % 3 == 0) && div5.isChecked == (numero % 5 == 0) && div10.isChecked == (numero % 10 == 0))) {
                    aciertoComp = "Correcto"
                    imagenI = R.drawable.ok
                } else {
                    aciertoComp = "Error"
                    imagenI = R.drawable.ko
                }
            }

            respuesta.text = aciertoComp
        }

        imagen.setImageResource(imagenI)
    }
}