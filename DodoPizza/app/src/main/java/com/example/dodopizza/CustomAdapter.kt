package com.example.dodopizza

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(
    private val context: Context,
    private var data: Array<CustomAdapter.MenuItem>
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.imageView.setImageResource(item.imageResource)
        holder.textName.text = item.name
        holder.textDescription.text = item.description
        holder.textCost.text = item.cost
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(newData: Array<CustomAdapter.MenuItem>) {
        data = newData
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textName: TextView = itemView.findViewById(R.id.NameView)
        val textDescription: TextView = itemView.findViewById(R.id.DescriptionView)
        val textCost: TextView = itemView.findViewById(R.id.CostView)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    itemClickListener?.onItemClick(data[position])
//                    val intent = Intent(context, OrderActivity::class.java)
//                    intent.putExtra("image", data[position].imageResource)
//                    intent.putExtra("name", data[position].name)
//                    intent.putExtra("description", data[position].description)
//                    intent.putExtra("cost", data[position].cost)
//                    context.startActivity(intent)
                }
            }
        }

        fun bind(item: CustomAdapter.MenuItem) {
            imageView.setImageResource(item.imageResource)
            textName.text = item.name
            textDescription.text = item.description
            textCost.text = item.cost
        }

    }

    data class MenuItem(
        val imageResource: Int,
        val name: String,
        val description: String,
        val cost: String
    )

    interface OnItemClickListener {
        fun onItemClick(item: CustomAdapter.MenuItem)
    }

    private var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.itemClickListener = listener
    }

}
