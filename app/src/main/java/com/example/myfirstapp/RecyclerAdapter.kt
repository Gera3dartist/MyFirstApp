package com.example.myfirstapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.databinding.FragmentItemLayoutBinding


class RecyclerAdapter(private val context: Context, private val foodItemList:MutableList<ItemData>)
    : RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = FragmentItemLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val foodItem = foodItemList[position]
        holder.bind(foodItem)
    }

    override fun getItemCount(): Int {
        return foodItemList.size
    }


    class ItemViewHolder(foodItemLayoutBinding: FragmentItemLayoutBinding)
        : RecyclerView.ViewHolder(foodItemLayoutBinding.root){

        private val binding = foodItemLayoutBinding

        fun bind(item: ItemData){
            binding.itemName.text = item.name
            binding.itemDescription.text = item.description
        }

    }
}