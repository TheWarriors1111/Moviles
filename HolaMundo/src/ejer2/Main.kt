package ejer2

import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    var lista = ArrayList<Persona>()

    var repetir : Char = 'S'
    while (repetir == 'S') {
        println("dime el nombre de la persona")
        var nombre = scanner.nextLine()
        println("dime el peso de la persona")
        var peso: Double = scanner.nextLine().toDouble()
        println("dime la altura de la persona")
        var altura = scanner.nextLine().toDouble()

        lista.add(Persona(nombre, peso, altura))

        println("indicame si quieres introducir más datos de persona (S/N)")
        repetir = scanner.nextLine().toCharArray()[0]
    }

    for (i in lista) {
        println(i.toString())
    }
}