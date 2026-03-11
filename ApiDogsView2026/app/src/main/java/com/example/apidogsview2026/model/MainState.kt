package com.example.apidogsview2026.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class MainState {
    val retrofitApi = RetrofitApi()
    lateinit var misDatos: Datos
    lateinit var fotosPerrosCargado: DogRespuesta

    suspend fun recuperarFotosPaginacion(raza: String): Datos = withContext(Dispatchers.IO) {

        val respuesta = retrofitApi.retrofitService.getFotosPerros(raza)

        if (respuesta.isSuccessful) {
            fotosPerrosCargado = DogRespuesta(respuesta.body()!!.message, respuesta.body()!!.status)
            if (fotosPerrosCargado.message!!.size > 0) {
                var numPaginas: Int = fotosPerrosCargado.message!!.size/10
                if (fotosPerrosCargado.message!!.size%10 != 0) {
                    numPaginas++
                }
                misDatos = Datos(mutableListOf(), numPaginas, 1, fotosPerrosCargado.status)
                var rango = Math.min(fotosPerrosCargado.message!!.size-1,9)
                for (i in 0..rango) {
                    misDatos.message!!.add(fotosPerrosCargado.message!![i])
                }
            }
            misDatos!!
        } else {
            misDatos = Datos(null,null,null,"error")
            misDatos!!
        }

    }

    fun scrollFotos(): Datos {
        var inicio: Int
        var fin: Int
        inicio = misDatos.paginaActual!!*10
        misDatos.paginaActual = misDatos.paginaActual!! + 1
        if (misDatos.paginaActual!! < misDatos.numPaginas!!) {
            fin = (misDatos.paginaActual!!*10-1)
        } else {
            fin = (fotosPerrosCargado.message!!.size-1)
        }
        for (i in inicio..fin) {
            misDatos.message!!.add(fotosPerrosCargado.message!![i])
        }
        return misDatos
    }
}