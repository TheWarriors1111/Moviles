package com.example.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.fragmentos.databinding.FragmentDivisiblesBinding
import com.example.fragmentos.model.Datos
import com.example.fragmentos.viewmodel.CViewModel
import kotlin.getValue

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
/**
 * A simple [Fragment] subclass.
 * Use the [DivisiblesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DivisiblesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding3: FragmentDivisiblesBinding
    private val miViewModel: CViewModel by viewModels()
    private var datosIniciales : Int? = null
    var resp = Datos(false, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            datosIniciales = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding3 = FragmentDivisiblesBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding3.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding3.btValidar2.setOnClickListener {
            if (!binding3.cb2.isChecked && !binding3.cb3.isChecked && !binding3.cb5.isChecked && !binding3.cb10.isChecked && !binding3.cbNo.isChecked) {
                Toast.makeText(
                    binding3.root.context,
                    "Debes elegir una opción",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                var listaCb : List<Boolean> = listOf(binding3.cb2.isChecked, binding3.cb3.isChecked, binding3.cb5.isChecked, binding3.cb10.isChecked, binding3.cbNo.isChecked)

                miViewModel.comprobarDivisible(datosIniciales!!, listaCb)
            }
        }

        miViewModel.misDatosObservables.observe(viewLifecycleOwner) {
            if (it.estado) {
                binding3.txtValidacion2.text = "Correcto"
            } else {
                binding3.txtValidacion2.text = "Incorrecto"
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DivisiblesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(datosPasados: Int) =
            DivisiblesFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, datosPasados)
                }
            }
    }
}