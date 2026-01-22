package ejer3

import java.util.Random

class Revolver {
    var posicionBala: Int = 0
    var posicionTambor: Int = 0

    init {
        iniciarRev()
    }

    fun iniciarRev() {
        posicionBala = (Math.random() * 6).toInt()
        posicionTambor = (Math.random() * 6).toInt()
    }

    fun disparar(): Boolean{
        if (posicionBala == posicionTambor) {
            return true
        } else {
            return false
        }
    }
}