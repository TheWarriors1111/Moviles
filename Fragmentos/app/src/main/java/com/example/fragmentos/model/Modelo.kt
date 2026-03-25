package com.example.fragmentos.model

class Modelo {
    var misDatos = Datos(false, 0)

    public fun generarAleatorio(): Datos {
        var numGen: Int = (Math.random() * (2200 - 1900 + 1) + 1900).toInt()
        misDatos.numAlea = numGen
        misDatos.estado = true

        return misDatos
    }

    public fun comprobarBisiesto(comprobar: Datos): Datos {
        misDatos.numAlea = comprobar.numAlea
        var esBisiesto: Boolean

        if (misDatos.numAlea % 4 == 0) {
            if (misDatos.numAlea % 100 == 0) {
                if (misDatos.numAlea % 400 == 0) {
                    esBisiesto = true
                } else {
                    esBisiesto = false
                }
            } else {
                esBisiesto = true
            }
        } else {
            esBisiesto = false
        }

        if (esBisiesto == comprobar.estado) {
            misDatos.estado = true
        } else {
            misDatos.estado = false
        }

        return misDatos
    }

    public fun comprobarDivisible(numAlea: Int, listCb: List<Boolean>): Datos {
        misDatos.numAlea = numAlea
        var esCorrecto: Boolean

        if (!listCb[4]) {
            if (listCb[0] == (misDatos.numAlea % 2 == 0) && listCb[1] == (misDatos.numAlea % 3 == 0) && listCb[2] == (misDatos.numAlea % 5 == 0) && listCb[3] == (misDatos.numAlea % 10 == 0)) {
                esCorrecto = true
            } else {
                esCorrecto = false
            }
        } else {
            if ((listCb[0] == (misDatos.numAlea % 2 == 0) && listCb[1] == (misDatos.numAlea % 3 == 0) && listCb[2] == (misDatos.numAlea % 5 == 0) && listCb[3] == (misDatos.numAlea % 10 == 0))) {
                esCorrecto = true
            } else {
                esCorrecto = false
            }
        }

        misDatos.estado = esCorrecto
        return misDatos
    }
}