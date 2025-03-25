package com.example.mycookbook

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView

class IngridientAdapter(context: Context, private val resource: Int, private val items: MutableList<String>, private val sender: String) :
    ArrayAdapter<String>(context, resource, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view = convertView ?: LayoutInflater.from(context).inflate(resource, parent, false)

        val text: TextView = view.findViewById(R.id.ingridient)
        val item = items[position]
        text.text = "• ${item}"
        val button: Button = view.findViewById(R.id.delete_ingridient)
        button.setOnClickListener {
            if (position in items.indices) {
                items.removeAt(position)
                notifyDataSetChanged()
            }
        }

        if(sender =="details"){
            button.visibility = View.GONE
        }

        return view

    }




}