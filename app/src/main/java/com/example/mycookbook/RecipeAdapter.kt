package com.example.mycookbook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter(private val recipeList: List<Recipe>):
    RecyclerView.Adapter<RecipeAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder{
        val recipeView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerlist, parent, false)
        return MyViewHolder(recipeView)
    }

    override fun getItemCount(): Int = recipeList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val recipe = recipeList[position]


    }

    class MyViewHolder(recipeView: View): RecyclerView.ViewHolder(recipeView){
        val recipeName: TextView = recipeView.findViewById(R.id.recipe_name)
    }

}