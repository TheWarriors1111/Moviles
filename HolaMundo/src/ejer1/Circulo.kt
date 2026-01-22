package ejer1

import kotlin.math.pow

class Circulo(nombre: String, radio: Double) : Forma(nombre, Tipos.circulo), Calculo {
    var radio: Double = radio
    override fun calcularArea(): Double {
        return radio.pow(2.0)
    }

    override fun toString(): String {
        return "${super<Forma>.toString()}, superficie: ${calcularArea()}"
    }
}