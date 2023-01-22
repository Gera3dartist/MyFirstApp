package com.example.myfirstapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfirstapp.databinding.FragmentThirdBinding

/**
 * A simple [Fragment] subclass as the third destination in the navigation.
 */
class ThirdFragment : Fragment() {
    private lateinit var adapter: RecyclerAdapter
    private val sensorItemsList: MutableList<Sensor> = mutableListOf()

    private var _binding: FragmentThirdBinding? = null
    private var dateBaseHelper: DbHandler? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        setUpAdapter()

        dateBaseHelper = context?.let { DbHandler(it, null, null, 1) };
        populateList()
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.thirdButtonAdd.setOnClickListener {
            val sensor = Sensor(
                name=binding.thirdTextView.text.toString(),
                value=binding.thirdTextView.text.toString().length
            )

            dateBaseHelper?.addSensor(sensor)
            sensorItemsList.add(sensor)
            binding.itemsRV.adapter?.notifyItemInserted(sensorItemsList.size - 1)
            binding.thirdTextView.setText("")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpAdapter() {
        adapter = RecyclerAdapter(binding.root.context, sensorItemsList)

        binding.itemsRV.adapter = adapter
        binding.itemsRV.layoutManager = LinearLayoutManager(binding.root.context)
    }

    private fun populateList() {

        for (i in 1..2){
            val sensor = Sensor(name="Sensor$i", value=i)

            dateBaseHelper?.addSensor(sensor)

            sensorItemsList.add(sensor)
        }
        val result = dateBaseHelper?.listSensors()
        result?.let { sensorItemsList.addAll(it) }

    }

}