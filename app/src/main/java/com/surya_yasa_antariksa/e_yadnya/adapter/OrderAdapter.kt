package com.surya_yasa_antariksa.e_yadnya.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.surya_yasa_antariksa.e_yadnya.R
import com.surya_yasa_antariksa.e_yadnya.model.OrderItemModel
import kotlinx.android.synthetic.main.activity_list_item_order.view.*

class OrderAdapter(private val items: List<OrderItemModel>) :
    RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_list_item_order, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.nameView.text = item.name
        holder.priceView.text = item.price.toString()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameView: TextView = itemView.item_name
        val priceView: TextView = itemView.item_price
    }
}
