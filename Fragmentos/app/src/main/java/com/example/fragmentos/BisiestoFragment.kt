package com.example.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.example.fragmentos.databinding.FragmentBisiestoBinding
import com.example.fragmentos.model.Datos
import com.example.fragmentos.viewmodel.CViewModel
import kotlin.getValue

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BisiestoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BisiestoFragment : Fragment() {
    private lateinit var binding2: FragmentBisiestoBinding

    // TODO: Rename and change types of parameters
    private var datosIniciales: Int? = null
    private val miViewModel: CViewModel by viewModels()
    var resp = Datos(false, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            datosIniciales = it.getInt(ARG_PARAM2)
            //param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding2 = FragmentBisiestoBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding2.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding2.btValidar.setOnClickListener {
            if (!binding2.rbSi.isChecked && !binding2.rbNo.isChecked) {
                Toast.makeText(
                    binding2.root.context,
                    "Debes elegir una opción",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                var esBisiesto: Boolean = binding2.rbSi.isChecked
                miViewModel.comprobarBisiesto(Datos(esBisiesto, datosIniciales!!))
            }
        }

        miViewModel.misDatosObservables.observe(viewLifecycleOwner) {
            if (it.estado) {
                binding2.txtValidacion.text = "Correcto"
            } else {
                binding2.txtValidacion.text = "Incorrecto"
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
         * @return A new instance of fragment BisiestoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(datosPasados: Int) =
            BisiestoFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM2, datosPasados)
                }
            }
    }
}