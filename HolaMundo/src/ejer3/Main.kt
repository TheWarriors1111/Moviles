package ejer3

import java.util.Scanner

fun main(){
    val scanner = Scanner(System.`in`)
    val jugadores = mutableListOf<Jugador>()
    val revolver = Revolver()

    println("dime num de jugadores")
    var numJugadores = scanner.nextLine().toInt()
    for (i in 1..numJugadores) {
        println("nombre del jugador")
        jugadores.add(Jugador(nombre = scanner.nextLine()))
    }

    while (contarVivos(jugadores) != 1) {
        for (jugador in jugadores) {
            println("turno de ${jugador.nombre}")
            println("quieres jugar (S/N)?")
            var res = scanner.nextLine()
            if (res=="S") {
                println("eres valiente, giramos y disparamos el revolver")
                if (revolver.disparar()){
                    println("PUUUUMMMM \n Metemos otra bala en el tambor y volvemos a girar")
                    revolver.iniciarRev()
                    jugador.vivo = false
                    println("Una pena, ${jugador.nombre} era un buen amigo. RIP")
                } else {
                    println("CLICK")
                    revolver.iniciarRev()
                    println("WOW, ${jugador.nombre} ha esquivado la bala")
                }
            } else{
                jugador.vivo = false
            }

        }
    }
    var nombreV: String = ""
    for (jugador in jugadores) {
        if (jugador.vivo) {
            nombreV = jugador.nombre
        }
    }

    println("se acabo la partida, solo ha quedado vivo ${nombreV}")


}

fun contarVivos(lista: List<Jugador>): Int {
    var cont = 0
    for (j in lista) {
        if (j.vivo) {
            cont++
        }
    }
    return cont
}