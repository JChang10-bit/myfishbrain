package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_second.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    lateinit var comm: Communicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        comm = activity as Communicator

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spinner1: Spinner = weight_options
        val spinner2: Spinner = species_option
        val spinner3: Spinner = lure_type_options
        val spinner4: Spinner = rod_power_options
        val spinner5: Spinner = rod_length_options
        val spinner6: Spinner = reel_ratio_options
        val spinner7: Spinner = fishing_line_options
        val spinner8: Spinner = line_pounds_options
        val spinner9: Spinner = water_conditions_options

        ArrayAdapter.createFromResource(
            requireContext(), R.array.weight_options, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner1.adapter = adapter
        }
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.species_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner2.adapter = adapter
        }
        ArrayAdapter.createFromResource(
            requireContext(), R.array.lure_type_options, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner3.adapter = adapter
        }
        ArrayAdapter.createFromResource(
            requireContext(), R.array.rod_power_options, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner4.adapter = adapter
        }
        ArrayAdapter.createFromResource(
            requireContext(), R.array.rod_length_options, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner5.adapter = adapter
        }
        ArrayAdapter.createFromResource(
            requireContext(), R.array.reel_ratio_options, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner6.adapter = adapter
        }
        ArrayAdapter.createFromResource(
            requireContext(), R.array.fishing_line_options, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner7.adapter = adapter
        }
        ArrayAdapter.createFromResource(
            requireContext(), R.array.line_pounds_options, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner8.adapter = adapter
        }
        ArrayAdapter.createFromResource(
            requireContext(), R.array.water_conditions, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner9.adapter = adapter
        }

        view.findViewById<Button>(R.id.cancel_button).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        view.findViewById<Button>(R.id.confirm_button).setOnClickListener {
            val catchInfo: CatchInfo = createCatchInfo()
            comm.passDataCom(catchInfo)
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    private fun createCatchInfo(): CatchInfo {
        val catchDate: String = date_edit.text.toString()
        val weight: String = weight_options.selectedItem.toString()
        val species: String = species_option.selectedItem.toString()
        val location: String = location_edit.text.toString()
        val lureType: String = lure_type_options.selectedItem.toString()
        val rodPower: String = rod_power_options.selectedItem.toString()
        val rodLength: String = rod_length_options.selectedItem.toString()
        val reelRatio: String = reel_ratio_options.selectedItem.toString()
        val lineType: String = fishing_line_options.selectedItem.toString()
        val linePounds: String = line_pounds_options.selectedItem.toString()
        val waterConditions: String = water_conditions_options.selectedItem.toString()
        return CatchInfo(catchDate, weight, species, location, lureType,
            rodPower, rodLength, reelRatio, lineType, linePounds, waterConditions)
    }
}