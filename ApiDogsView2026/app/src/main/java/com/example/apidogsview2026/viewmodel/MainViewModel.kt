package com.example.apidogsview2026.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apidogsview2026.model.Datos
import com.example.apidogsview2026.model.DogRespuesta
import com.example.apidogsview2026.model.MainState
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val miEstado = MainState()
    private val _misDatos = MutableLiveData<Datos>()
    val datosObservables: LiveData<Datos> get() = _misDatos

    fun devuelvePerros(raza: String) {
        viewModelScope.launch {
            _misDatos.value = miEstado.recuperarFotosPaginacion(raza)
        }
    }

    fun scrollFotos() {
        viewModelScope.launch {
            _misDatos.value = miEstado.scrollFotos()
        }
    }
}