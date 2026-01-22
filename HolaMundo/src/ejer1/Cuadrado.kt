package ejer1

class Cuadrado(nombre: String,lado: Double) : Forma("cuadrado", Tipos.cuadrado), Calculo {
    var lado: Double = lado
    override fun calcularArea(): Double {
        return lado * lado
    }

    override fun toString(): String {
        return "${super<Forma>.toString()}, superficie: ${calcularArea()}"
    }

}