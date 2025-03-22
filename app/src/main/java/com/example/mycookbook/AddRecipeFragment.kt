package com.example.mycookbook

import android.content.Context
import android.media.Rating
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import android.widget.SeekBar
import android.widget.TextView

class AddRecipeFragment : Fragment() {
    lateinit var name : TextView
    lateinit var desc : TextView
    lateinit var tag : TextView
    lateinit var seek : SeekBar
    lateinit var seekText : TextView
    lateinit var rating: RatingBar
    lateinit var recipe: Recipe
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
        return inflater.inflate(R.layout.fragment_add_recipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tag = view.findViewById(R.id.main_tag)
        name = view.findViewById(R.id.edit_name)
        desc = view.findViewById(R.id.edit_desc)
        seek = view.findViewById(R.id.seekBar)
        seekText = view.findViewById(R.id.seek_text)
        var seekInt = 1
        rating = view.findViewById(R.id.rating)
        seek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                seekText.text = "trudność: ${p1}"
                seekInt =p1
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
        val recipeList = RecipeJsonManager.getRecipeListFromJson(requireContext())

        val button = view.findViewById<Button>(R.id.add_button)
        val editRecipe :Recipe? = arguments?.getParcelable<Recipe>("recipe")
        if (editRecipe != null) {
            tag.text = "Zaktualizuj"
            name.setText(editRecipe.nazwa)
            desc.setText(editRecipe.opis)
            seekText.text = "trudność: ${editRecipe.trudnosc}"
            seek.progress = editRecipe.trudnosc
            rating.rating = editRecipe.rating

            button.text = "Zaktualizuj przepis"
        }

        button.setOnClickListener{
            if (editRecipe != null){

                val updatedRecipe = Recipe(
                    id = editRecipe.id,
                    nazwa = name.text.toString(),
                    trudnosc = seek.progress,
                    rating = rating.rating,
                    opis = desc.text.toString()
                )
                val index = recipeList.indexOfFirst { it.id == editRecipe.id }
                if (index != -1) {
                    recipeList[index] = updatedRecipe
                    recipe = updatedRecipe
                }
                RecipeJsonManager.editRecipeList(requireContext(), recipeList)
            }else {
                recipe = Recipe(
                    recipeList.size,
                    name.text.toString(),
                    seekInt,
                    rating.rating,
                    desc.text.toString()
                )
                RecipeJsonManager.addRecipeToListJson(requireContext(), recipe)
            }
            listener.onDataPass(recipe, "add")
        }


    }

    companion object {

    }


}