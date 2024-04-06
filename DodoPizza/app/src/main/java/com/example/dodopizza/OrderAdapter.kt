package com.example.dodopizza

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OrderAdapter(private val items: List<Pair<Int, Pair<String, String>>>) :
    RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.in_list_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemImage: ImageView = itemView.findViewById(R.id.InimageView)
        private val itemName: TextView = itemView.findViewById(R.id.InNameView)
        private val itemDescription: TextView = itemView.findViewById(R.id.InDescriptionView)

        fun bind(item: Pair<Int, Pair<String, String>>) {
            itemImage.setImageResource(item.first)
            itemName.text = item.second.first
            itemDescription.text = item.second.second
        }
    }
}
