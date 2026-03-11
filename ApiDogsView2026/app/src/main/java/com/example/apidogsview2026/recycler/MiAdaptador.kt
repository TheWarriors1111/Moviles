package com.example.apidogsview2026.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apidogsview2026.MainActivity
import com.example.apidogsview2026.R
import com.example.apidogsview2026.model.DogRespuesta

class MiAdaptador(var misDatos : DogRespuesta): RecyclerView.Adapter<MiVista>() {
    override fun onCreateViewHolder(
        p0: ViewGroup,
        p1: Int
    ): MiVista {
        var miVista = LayoutInflater.from(p0.context).inflate(R.layout.mi_fila, p0, false)

        return MiVista(miVista)
    }

    override fun onBindViewHolder(
        p0: MiVista,
        p1: Int
    ) {
        val url : String = misDatos.message!![p1]
        Glide.with(p0.itemView.context).load(url).into(p0.imagen)
    }

    override fun getItemCount(): Int {
        return misDatos.message?.size ?: 0
    }
}