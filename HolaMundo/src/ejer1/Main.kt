package ejer1

fun main() {
    var triangulo = Triangulo("triangulo", 8.4, 3.5)
    println(triangulo.toString())

    var cuadrado = Cuadrado("cuadrado", 5.4)
    println(cuadrado.toString())

    var circulo = Circulo("circulo", 3.5)
    println(circulo.toString())

    var plano = Plano("plano")
    println(plano.toString())
}