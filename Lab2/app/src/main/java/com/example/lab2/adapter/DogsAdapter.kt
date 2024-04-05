package com.example.lab2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lab2.R
import com.example.lab2.entity.Dogs

class DogsAdapter : ListAdapter<Dogs, DogsAdapter.ViewHolder>(DogsDiffCallback()) {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dogNameTextView: TextView = itemView.findViewById(R.id.text_view_dog_name)
        private val dogImageView: ImageView = itemView.findViewById(R.id.image_view_dog)

        fun bind(dog: Dogs) {
            dogNameTextView.text = dog.name
            Glide.with(itemView)
                .load(dog.image_link)
                .placeholder(R.drawable.kebabpizza)
                .error(R.drawable.kebabpizza)
                .into(dogImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_dog, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

