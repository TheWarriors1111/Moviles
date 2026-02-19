package com.example.calculadorav2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculadorav2.model.Datos
import com.example.calculadorav2.model.Modelo
import kotlinx.coroutines.launch

class CViewModel : ViewModel() {
    val miModelo = Modelo()

    private val _misDatos = MutableLiveData<Datos>()
    val misDatosObservables: LiveData<Datos> get() = _misDatos

    public fun pasarSigno(signo: Char) {
        viewModelScope.launch {
            _misDatos.value = miModelo.pulsarSigno(signo)
        }
    }

    public fun pasarNumero(num: String) {
        viewModelScope.launch {
            _misDatos.value = miModelo.pulsarNum(num)
        }
    }

    public fun calcularResultado() {
        viewModelScope.launch {
            _misDatos.value = miModelo.darResultado()
        }
    }

    public fun limpiarOperaciones() {
        viewModelScope.launch {
            _misDatos.value = miModelo.limpiar()
        }
    }
}