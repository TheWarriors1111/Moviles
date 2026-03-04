package com.example.colores.recycler

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.core.graphics.toColor
import androidx.recyclerview.widget.RecyclerView
import com.example.colores.R
import com.example.colores.model.Datos
import kotlin.collections.get
import androidx.core.graphics.toColorInt

class MiAdaptador(var misDatos: Datos): RecyclerView.Adapter<MiVista>() {
    var posicionSeleccionada = RecyclerView.NO_POSITION
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MiVista {
        var miVista = LayoutInflater.from(parent.context).inflate(R.layout.mi_fila, parent, false)

        return MiVista(miVista)
    }

    override fun onBindViewHolder(
        holder: MiVista,
        position: Int
    ) {
        holder.miTexto.text = misDatos.lista[position].nombreColor
        holder.miTexto2.text = misDatos.lista[position].colorHex
        holder.contenedor.setBackgroundColor(misDatos.lista[position].colorHex.toColorInt())

        val colorTexto = Color.WHITE
        val colorFondo = misDatos.lista[position].colorHex.toColorInt()

        if (position == posicionSeleccionada) {
            holder.contenedor.setBackgroundColor(colorTexto)
            holder.miTexto.setTextColor(colorFondo)
            holder.miTexto2.setTextColor(colorFondo)
        } else {
            holder.contenedor.setBackgroundColor(colorFondo)
            holder.miTexto.setTextColor(colorTexto)
            holder.miTexto2.setTextColor(colorTexto)
        }

        holder.contenedor.setOnClickListener {
            notifyItemChanged(posicionSeleccionada)
            posicionSeleccionada = position
            notifyItemChanged(posicionSeleccionada)
        }
    }

    override fun getItemCount(): Int {
        return misDatos.lista.size
    }
}