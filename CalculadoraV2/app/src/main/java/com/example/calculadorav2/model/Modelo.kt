package com.example.calculadorav2.model

class Modelo {
    var misDatos = Datos("", "", 'I', 0.0, false)

    public fun pulsarSigno(signo: Char): Datos {
        if (misDatos.signo != '+' && misDatos.signo != '-' && misDatos.signo != '*' && misDatos.signo != '/') {
            misDatos.error = false
            misDatos.signo = signo
        } else {
            misDatos.error = true
        }

        return misDatos
    }

    public fun pulsarNum(num: String): Datos {
        misDatos.error = false
        if (misDatos.terminado) {
            misDatos = Datos("", "", 'I', 0.0, false)
        }
        if (misDatos.signo == 'I') {
            misDatos.num1 += num
        } else {
            misDatos.num2 += num
        }

        return misDatos
    }

    public fun darResultado(): Datos {
        val signo: Char = misDatos.signo
        var resultado: Double = 0.0

        when (signo) {
            '+' -> resultado = misDatos.num1.toDouble() + misDatos.num2.toDouble()
            '-' -> resultado = misDatos.num1.toDouble() - misDatos.num2.toDouble()
            '*' -> resultado = misDatos.num1.toDouble() * misDatos.num2.toDouble()
            '/' -> resultado = misDatos.num1.toDouble() / misDatos.num2.toDouble()
        }

        misDatos.signo = '='
        misDatos.terminado = true

        misDatos.resultado = resultado

        return misDatos
    }

    public fun limpiar(): Datos {
        misDatos = Datos("", "", 'I', 0.0, false)

        return misDatos
    }
}