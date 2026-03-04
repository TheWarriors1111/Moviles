package com.example.colores.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.colores.model.ColorModel
import com.example.colores.model.Datos
import com.example.colores.model.MiColor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ColorViewModel: ViewModel() {
    var miModelo = ColorModel()
    private var _datos = MutableStateFlow<Datos>(Datos("", mutableListOf<MiColor>()))

    val datos: StateFlow<Datos> get() =_datos

    public fun devolverLista() {
        viewModelScope.launch {
            _datos.value = miModelo.devolverLista()
        }
    }
}