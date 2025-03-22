package com.example.mycookbook

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView


class RecipeDetailsFragment : Fragment() {
    lateinit var name : TextView
    lateinit var desc : TextView
    lateinit var ratingBar: RatingBar
    lateinit var seekText: TextView
    private lateinit var listener: DataPassInterface

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is DataPassInterface){
            listener=context
        } else {
            throw RuntimeException("$context must implement DataPass")
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipe_details, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        name = view.findViewById(R.id.name)
        desc = view.findViewById(R.id.desc)
        ratingBar = view.findViewById(R.id.rating)
        seekText = view.findViewById(R.id.seek_text)

        val recipe = arguments?.getParcelable<Recipe>("recipe")
        recipe?.let { updateData(it) }
    }

    fun updateData(recipe: Recipe) {
        Log.i("abcd", "a")
        name.text=recipe.nazwa
        desc.text=recipe.opis
        seekText.text= "trudność: ${recipe.trudnosc}"
        ratingBar.rating = recipe.rating
    }

}

