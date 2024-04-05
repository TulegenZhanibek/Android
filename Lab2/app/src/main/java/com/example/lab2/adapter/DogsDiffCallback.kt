package com.example.lab2.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.lab2.entity.Dogs

class DogsDiffCallback : DiffUtil.ItemCallback<Dogs>() {
    override fun areItemsTheSame(oldItem: Dogs, newItem: Dogs): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Dogs, newItem: Dogs): Boolean {
        return oldItem == newItem
    }
}
