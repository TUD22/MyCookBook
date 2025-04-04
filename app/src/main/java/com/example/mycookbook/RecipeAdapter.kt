package com.example.mycookbook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter(private val recipeList: List<Recipe>, private val clickListener: (Recipe) -> Unit):
    RecyclerView.Adapter<RecipeAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder{
        val recipeView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerlist, parent, false)
        return MyViewHolder(recipeView)
    }

    override fun getItemCount(): Int = recipeList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val recipe = recipeList[position]
        holder.recipeName.text = recipe.nazwa
        holder.recipeRating.rating = recipe.rating

        holder.itemView.setOnClickListener{
            clickListener(recipe)
        }

    }

    class MyViewHolder(recipeView: View): RecyclerView.ViewHolder(recipeView){
        val recipeName: TextView = recipeView.findViewById(R.id.recipe_name)
        val recipeRating: RatingBar = recipeView.findViewById(R.id.rating)
    }

}