package com.example.fragmentos

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.fragmentos.databinding.ActivityMainBinding
import com.example.fragmentos.model.Datos
import com.example.fragmentos.viewmodel.CViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var bisiestoFragment: BisiestoFragment
    lateinit var divisiblesFragment: DivisiblesFragment
    private val miViewModel: CViewModel by viewModels()
    var resp = Datos(false, 0)
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
        setSupportActionBar(binding.barra)

        binding.btnGenerar.setOnClickListener {
            miViewModel.generarAleatorio()
        }

        binding.barraInferior.setOnItemSelectedListener {
            try {
                when (it.itemId) {
                    R.id.item_1 -> {
                        if (resp.estado) {
                            bisiestoFragment = BisiestoFragment.newInstance(resp.numAlea)
                            cargarFragmento(bisiestoFragment)
                            true
                        } else {
                            Toast.makeText(
                                this@MainActivity,
                                "Debes generar un número",
                                Toast.LENGTH_SHORT
                            ).show()
                            false
                        }
                    }

                    R.id.item_2 -> {
                        if (resp.estado) {
                            divisiblesFragment = DivisiblesFragment.newInstance(resp.numAlea)
                            cargarFragmento(divisiblesFragment)
                            true
                        } else {
                            Toast.makeText(
                                this@MainActivity,
                                "Debes generar un número",
                                Toast.LENGTH_SHORT
                            ).show()
                            false
                        }
                    }

                    else -> {
                        true
                    }
                }
            } catch (e: Exception) {
                throw e
            }
        }

        miViewModel.misDatosObservables.observe(this@MainActivity) {
            if (it.estado) {
                resp = it
                binding.txtNum.text = resp.numAlea.toString()

                when (binding.barraInferior.selectedItemId) {
                    R.id.item_1 -> {
                        bisiestoFragment = BisiestoFragment.newInstance(resp.numAlea)
                        cargarFragmento(bisiestoFragment)
                    }

                    R.id.item_2 -> {
                        divisiblesFragment = DivisiblesFragment.newInstance(resp.numAlea)
                        cargarFragmento(divisiblesFragment)
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        binding.barra.inflateMenu(R.menu.menu_main)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.title) {
            "salir" -> this.finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun cargarFragmento(fragment: Fragment) {

        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.contFrame, fragment)
            transaction.commit()
        }
    }
}