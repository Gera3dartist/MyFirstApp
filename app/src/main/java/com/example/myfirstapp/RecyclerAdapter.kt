package com.example.myfirstapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.databinding.FragmentItemLayoutBinding


class RecyclerAdapter(private val context: Context, private val sensorItemList:MutableList<Sensor>)
    : RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = FragmentItemLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val sensorItem = sensorItemList[position]
        holder.bind(sensorItem)
    }

    override fun getItemCount(): Int {
        return sensorItemList.size
    }


    class ItemViewHolder(sensorItemLayoutBinding: FragmentItemLayoutBinding)
        : RecyclerView.ViewHolder(sensorItemLayoutBinding.root){

        private val binding = sensorItemLayoutBinding

        fun bind(item: Sensor){
            binding.itemName.text = item.name
            binding.itemValue.text = item.value.toString()
        }

    }
}