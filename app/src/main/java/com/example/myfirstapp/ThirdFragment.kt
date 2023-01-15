package com.example.myfirstapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.databinding.FragmentThirdBinding

/**
 * A simple [Fragment] subclass as the third destination in the navigation.
 */
class ThirdFragment : Fragment() {
    private lateinit var adapter: RecyclerAdapter
    private val foodItemsList: MutableList<ItemData> = mutableListOf()

    private var _binding: FragmentThirdBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        setUpAdapter()
        populateList()
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.thirdButtonAdd.setOnClickListener {
//
            foodItemsList.add(
                ItemData(name = binding.thirdTextView.text.toString(), description = "default"))
            binding.itemsRV.adapter?.notifyItemInserted(foodItemsList.size - 1)
            binding.thirdTextView.setText("")
        }
//
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpAdapter() {
        adapter = RecyclerAdapter(binding.root.context, foodItemsList)

        binding.itemsRV.adapter = adapter
        binding.itemsRV.layoutManager = LinearLayoutManager(binding.root.context)
    }

    private fun populateList() {
        for (i in 1..2){
            val name = "Food Item $i"
            val description = "Description $i"

            val foodItem = ItemData(name = name, description = description)

            foodItemsList.add(foodItem)
        }
    }
}