package com.example.fragmentos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fragmentos.model.Datos
import com.example.fragmentos.model.Modelo
import kotlinx.coroutines.launch

class CViewModel : ViewModel() {
    val miModelo = Modelo()

    private val _misDatos = MutableLiveData<Datos>()
    val misDatosObservables: LiveData<Datos> get() = _misDatos

    public fun generarAleatorio() {
        viewModelScope.launch {
            _misDatos.value = miModelo.generarAleatorio()
        }
    }

    public fun comprobarBisiesto(comprobar: Datos) {
        viewModelScope.launch {
            _misDatos.value = miModelo.comprobarBisiesto(comprobar)
        }
    }

    public fun comprobarDivisible(numAlea: Int, listCb: List<Boolean>) {
        viewModelScope.launch {
            _misDatos.value = miModelo.comprobarDivisible(numAlea, listCb)
        }
    }
}