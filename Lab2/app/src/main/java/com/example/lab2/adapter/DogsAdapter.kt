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
    private var dogsListFull: List<Dogs> = ArrayList()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dogNameTextView: TextView = itemView.findViewById(R.id.text_view_dog_name)
        private val dogImageView: ImageView = itemView.findViewById(R.id.image_view_dog)
        private val dogsheddingView: TextView = itemView.findViewById(R.id.text_view_dog_shedding)
        private val dogsgroomingView: TextView = itemView.findViewById(R.id.text_view_dog_grooming)
        private val dogsdroolingView: TextView = itemView.findViewById(R.id.text_view_dog_drooling)

        fun bind(dog: Dogs) {
            dogNameTextView.text = dog.name
            dogsheddingView.text = "Shedding: ${dog.shedding}"
            dogsgroomingView.text = "Grooming: ${dog.grooming}"
            dogsdroolingView.text = "Drooling: ${dog.drooling}"
            Glide.with(itemView)
                .load(dog.image_link)
                .placeholder(R.drawable.kebabpizza)
                .error(  R.drawable.kebabpizza)
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

    override fun submitList(list: List<Dogs>?) {
        super.submitList(list)
        list?.let {
            dogsListFull = ArrayList(it)
        }
    }
    fun filter(query: String) {
        val filteredList = if (query.isBlank()) {
            dogsListFull
        } else {
            dogsListFull.filter { it.name.contains(query, ignoreCase = true) }
        }
        submitList(filteredList)
    }
}

