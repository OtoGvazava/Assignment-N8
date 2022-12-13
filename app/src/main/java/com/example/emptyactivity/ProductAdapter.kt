package com.example.emptyactivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter (private val products: List<ProductModel>): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_view_design, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.textViewName.text = product.name
        holder.textViewDescription.text = product.description
    }

    override fun getItemCount(): Int {
        return products.size
    }

    class ViewHolder(productView: View): RecyclerView.ViewHolder(productView) {
        val textViewName: TextView = productView.findViewById(R.id.textViewName)
        val textViewDescription: TextView = productView.findViewById(R.id.textViewDescription)
    }
}