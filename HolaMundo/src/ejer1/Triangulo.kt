package ejer1

class Triangulo(nombre: String, base: Double, altura: Double) : Forma("triangulo", Tipos.triangulos),
    Calculo {
    var base:Double = base
    var altura:Double = altura
    override fun calcularArea(): Double {
        return (base*altura)/2
    }

    override fun toString(): String {
        return "${super<Forma>.toString()}, superficie: ${calcularArea()}"
    }

}