package com.example.colores.recycler

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.colores.R

class MiVista(miFila: View): RecyclerView.ViewHolder(miFila) {
    var miTexto =miFila.findViewById<TextView>(R.id.txt1)
    var miTexto2 = miFila.findViewById<TextView>(R.id.txt2)

    var contenedor = miFila.findViewById<LinearLayout>(R.id.ll1)
}