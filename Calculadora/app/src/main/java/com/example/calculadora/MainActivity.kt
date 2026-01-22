package com.example.calculadora

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var resultadoTexto: TextView
    lateinit var num1: TextView
    lateinit var num2: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        resultadoTexto = findViewById<TextView>(R.id.txRes)
        num1 = findViewById<TextView>(R.id.opc1text)
        num2 = findViewById<TextView>(R.id.opc2text)
    }

    fun sumar(v: View) {
        var n1 = Integer.parseInt(num1.text.toString())
        var n2 = Integer.parseInt(num2.text.toString())
        resultadoTexto.text = (n1.plus(n2)).toString()
    }

    fun restar(v: View) {
        var n1 = Integer.parseInt(num1.text.toString())
        var n2 = Integer.parseInt(num2.text.toString())
        resultadoTexto.text = (n1 - n2).toString()
    }

    fun multiplicar(v: View) {
        var n1 = Integer.parseInt(num1.text.toString())
        var n2 = Integer.parseInt(num2.text.toString())
        resultadoTexto.text = (n1 * n2).toString()
    }

    fun dividir(v: View) {
        var n1 = Integer.parseInt(num1.text.toString()).toDouble()
        var n2 = Integer.parseInt(num2.text.toString()).toDouble()
        resultadoTexto.text = (n1.div(n2)).toString()
    }
}