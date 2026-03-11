package com.example.apidogsview2026

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.get
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apidogsview2026.databinding.ActivityMainBinding
import com.example.apidogsview2026.databinding.MiFilaBinding
import com.example.apidogsview2026.model.Datos
import com.example.apidogsview2026.model.DogRespuesta
import com.example.apidogsview2026.recycler.MiAdaptador
import com.example.apidogsview2026.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var miAdaptador: MiAdaptador
    private val miViewModel: MainViewModel by viewModels()

    //var resp = DogRespuesta(null, "ok")
    var resp = Datos(null, null, null, "null")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.main
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btn1.setOnClickListener {
            if (!binding.entradaTexto.text.toString().isEmpty()) {
                miViewModel.devuelvePerros(binding.entradaTexto.text.toString())
            }
        }

        binding.rvPerros.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                var finalScroll: Boolean = false
                val lm: LinearLayoutManager = binding.rvPerros.layoutManager as LinearLayoutManager
                if (lm.findLastVisibleItemPosition() % 10 >= 9 &&
                    lm.findLastVisibleItemPosition() / 10 == (resp.paginaActual!! - 1)
                ) {
                    finalScroll = true
                }
                if (finalScroll && resp.paginaActual!! < resp.numPaginas!!) {
                    Snackbar.make(
                        binding.main,
                        "Si desea recuperar mas fotos pulse: ",
                        Snackbar.LENGTH_LONG
                    ).setAction(
                        "Cargar más fotos", {
                            miViewModel.scrollFotos()
                        }
                    ).show()
                }
            }
        })

        miViewModel.datosObservables.observe(this@MainActivity) {
            when (it.status) {
                "success" -> {
                    if (it.paginaActual == 1) {
                        resp = it
                        miAdaptador = MiAdaptador(DogRespuesta(it.message, it.status))
                        binding.rvPerros.layoutManager = LinearLayoutManager(
                            this@MainActivity,
                            LinearLayoutManager.VERTICAL, false
                        )
                        binding.rvPerros.adapter = miAdaptador

                        val miDividerItemDecoration =
                            DividerItemDecoration(
                                this@MainActivity,
                                DividerItemDecoration.VERTICAL
                            )
                        binding.rvPerros.addItemDecoration(miDividerItemDecoration)
                    } else {
                        miAdaptador.notifyItemRangeChanged(
                            it.paginaActual!! * 10,
                            it.message!!.size
                        )
                    }
                }

                "error" -> Toast.makeText(
                    this@MainActivity,
                    "No hay fotos de esta raza",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}