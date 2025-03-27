package com.example.mycookbook

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class IngredientAdapter(
    private val context: Context,
    private val items: MutableList<String>,
    private val sender: String
) : RecyclerView.Adapter<IngredientAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById(R.id.ingridient)
        val button: Button = view.findViewById(R.id.delete_ingridient)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.ingridient, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.text.text = "• $item"

        if (sender == "details") {
            holder.button.visibility = View.GONE
        } else {
            holder.button.visibility = View.VISIBLE
            holder.button.setOnClickListener {
                if (position in items.indices) {
                    items.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, items.size)
                }
            }
        }
    }

    override fun getItemCount(): Int = items.size
}