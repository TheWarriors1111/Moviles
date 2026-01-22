import java.util.Calendar
import java.util.Locale
import java.util.Scanner
import java.util.TimeZone
import kotlin.time.Duration.Companion.days

fun main() {
    val sc = Scanner(System.`in`);
    println("1 - Saber si un día es laborable o fin de semana\n2 - Divisibles entre 3\n3 - Siguiente divisible entre 7\n4 - Notas del aulta\n5 - Divisibles entre 11\n6 - Calcula el precio del cine")
    var opc = Integer.parseInt(sc.nextLine())

    if (opc == 1) {
        println("Dime el día: ")
        var dia = Integer.parseInt(sc.nextLine())
        println("Dime el mes: ")
        var mes = Integer.parseInt(sc.nextLine())
        println("Dime el año: ")
        var anio = Integer.parseInt(sc.nextLine())

        val calendario = Calendar.getInstance()
        calendario.set(anio, mes, dia)
        val cal = calendario.getTime()
        println(calendario.toString())
        val diaSemana: Int = calendario.get(Calendar.DAY_OF_WEEK)

        if (diaSemana == Calendar.SATURDAY || diaSemana == Calendar.SUNDAY) {
            println("El día elegido era laborable")
        }
    }
    if (opc == 2) {
        println("Dime el número del que debo partir: ")
        var numero = Integer.parseInt(sc.nextLine())
        for (i in numero..<numero + 25) {
            //println("$i resto ${i%3}")
            if (i % 3 == 0) {
                println("El número $i es divisible entre 3")
            }
        }
    }

    if (opc == 3) {
        println("Dime el número del que debo partir: ")
        var numero = Integer.parseInt(sc.nextLine())
        var encontrado = false
        while (!encontrado) {
            if (numero % 7 == 0) {
                println("El número $numero es divisible entre 7")
                encontrado = true
            }
            numero++
        }
    }

    if (opc == 4) {
        println("Cuántos alumnos son: ")
        var numAlumnos = Integer.parseInt(sc.nextLine())
        var listaNotas = ArrayList<Int>()
        var suma: Int = 0
        for (i in numAlumnos downTo 1) {
            println("Dame la siguiente nota: ")
            var nota = Integer.parseInt(sc.nextLine())
            listaNotas.add(nota)
            suma += nota
        }
        var media: Double = suma.toDouble() / numAlumnos
        println("La media de las notas introducidas es: $media")
        println("Y esta son las notas por encima de la media")
        for (numNota in listaNotas) {
            if (numNota > media) {
                println("La nota $numNota está por encima de la media")
            }
        }
    }

    if (opc == 5) {
        var nulos = true
        println("Dame un número del que debo partir: ")
        var numero = Integer.parseInt(sc.nextLine())
        println("Dime cuantos números a partir del anterior quieres que busque")
        var numLim = numero + Integer.parseInt(sc.nextLine())
        for (i in numero..numLim) {
            var num: Int? = calcularDiv11(i)
            if (num != null) {
                println("El número $num es divisible entre 11")
                nulos = false
            }
        }

        if (nulos) {
            println("No hay ningún número divisible entre 11 con los datos que has dado")
        }
    }

    if (opc == 6) {
        println("Dime cuantas entradas vas a comprar")
        var numero = Integer.parseInt(sc.nextLine())
        println()
    }

}

fun calcularDiv11(a: Int): Int? {
    return if (a % 11 == 0) {
        a
    } else {
        null
    }
}

fun calcularPrecioEntrada(edad: Int, miercoles: Boolean = false): Double {
    if (edad > 3 && edad < 14) {
        return 5.5
    } else if (edad > 65 && edad < 100) {
        return 4.5
    } else if (edad > 15 && edad < 64) {
        if (miercoles) {
            return 6.0
        } else {
            return 9.6
        }
    } else {
        return 0.0
    }
}