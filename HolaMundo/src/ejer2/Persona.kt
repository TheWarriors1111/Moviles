package ejer2

import kotlin.math.pow

class Persona(nombre: String, peso: Double, altura: Double) {
    var nombre: String = nombre
    var peso: Double = peso
    var altura: Double = altura

    fun calcularIMC(): Double {
        return (peso/(altura.pow(2.0)))
    }

    fun valorarIMC(): String {
        val imc = calcularIMC()
        return when {
            imc < 20.0 -> "Está por debajo de su peso ideal"
            imc in 20.0..25.0 -> "Está en su peso ideal"
            else -> "Está por encima de su peso ideal"
        }
    }

    override fun toString(): String {
        return "La persona ${nombre} y tiene un IMC de ${calcularIMC()} y eso implica ${valorarIMC()}"
    }
}