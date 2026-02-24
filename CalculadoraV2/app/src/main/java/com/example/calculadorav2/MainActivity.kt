package com.example.calculadorav2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculadorav2.databinding.KeyLandscapeBinding
import com.example.calculadorav2.model.Datos
import com.example.calculadorav2.viewmodel.CViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: KeyLandscapeBinding
    private val miViewModel: CViewModel by viewModels()
    var misDatos = Datos("", "", 'I', 0.0, false)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = KeyLandscapeBinding.inflate(layoutInflater)
        val view = binding.main
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btPlus.setOnClickListener {
            var signo: Char = binding.btPlus.text.toString().get(0)
            miViewModel.pasarSigno(signo)
        }

        binding.btMinus.setOnClickListener {
            var signo: Char = binding.btMinus.text.toString().get(0)
            miViewModel.pasarSigno(signo)
        }

        binding.btMultiply.setOnClickListener {
            var signo: Char = binding.btMultiply.text.toString().get(0)
            miViewModel.pasarSigno(signo)
        }

        binding.btSplit.setOnClickListener {
            var signo: Char = binding.btSplit.text.toString().get(0)
            miViewModel.pasarSigno(signo)
        }

        binding.btEqual.setOnClickListener {
            var signo: Char = binding.btEqual.text.toString().get(0)
            miViewModel.calcularResultado()
        }

        binding.btClear.setOnClickListener {
            miViewModel.limpiarOperaciones()
        }

        binding.bt0.setOnClickListener {
            miViewModel.pasarNumero(binding.bt0.text.toString())
        }

        binding.bt1.setOnClickListener {
            miViewModel.pasarNumero(binding.bt1.text.toString())
        }

        binding.bt2.setOnClickListener {
            miViewModel.pasarNumero(binding.bt2.text.toString())
        }

        binding.bt3.setOnClickListener {
            miViewModel.pasarNumero(binding.bt3.text.toString())
        }

        binding.bt4.setOnClickListener {
            miViewModel.pasarNumero(binding.bt4.text.toString())
        }

        binding.bt5.setOnClickListener {
            miViewModel.pasarNumero(binding.bt5.text.toString())
        }

        binding.bt6.setOnClickListener {
            miViewModel.pasarNumero(binding.bt6.text.toString())
        }

        binding.bt7.setOnClickListener {
            miViewModel.pasarNumero(binding.bt7.text.toString())
        }

        binding.bt8.setOnClickListener {
            miViewModel.pasarNumero(binding.bt8.text.toString())
        }

        binding.bt9.setOnClickListener {
            miViewModel.pasarNumero(binding.bt9.text.toString())
        }

        miViewModel.misDatosObservables.observe(this) { misDatosRecibidos ->
            if (misDatosRecibidos.error) {
                //Snackbar.make(binding.main, "Debes elegir números", Snackbar.LENGTH_SHORT).show()
                Toast.makeText(this, "Debes elegir números", Toast.LENGTH_SHORT).show()
            }

            if (misDatosRecibidos.num1 == "" || misDatosRecibidos.num2 == "") {
                binding.txtResultado.text = ""
                binding.txtHistorial.text = ""
            }

            if (misDatosRecibidos.signo == 'I') {
                binding.txtResultado.text = misDatosRecibidos.num1
            } else {
                if (misDatosRecibidos.signo != '=') {
                    binding.txtHistorial.text =
                        misDatosRecibidos.num1 + misDatosRecibidos.signo.toString()
                    binding.txtResultado.text = misDatosRecibidos.num2
                }
                if (misDatosRecibidos.terminado){
                    binding.txtHistorial.text =
                        misDatosRecibidos.num1 + misDatosRecibidos.signo + misDatosRecibidos.num2 + "="
                    binding.txtResultado.text = misDatosRecibidos.resultado.toString()
                }
            }
        }
    }
}