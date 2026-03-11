package com.example.apidogsview2026.recycler

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.apidogsview2026.R

class MiVista(miFila: View): RecyclerView.ViewHolder(miFila) {
    var imagen = miFila.findViewById<ImageView>(R.id.img1)
}