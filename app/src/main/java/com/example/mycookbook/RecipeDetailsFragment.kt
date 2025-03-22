package com.example.mycookbook

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView


class RecipeDetailsFragment : Fragment() {
    lateinit var name : TextView
    lateinit var desc : TextView
    lateinit var ratingBar: RatingBar
    lateinit var seekText: TextView
    lateinit var deleteButton : Button
    lateinit var button: Button
    lateinit var recipe: Recipe
    lateinit var recipeList : MutableList<Recipe>
    lateinit var newList : MutableList<Recipe>
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

        deleteButton = view.findViewById(R.id.delete_button)
        name = view.findViewById(R.id.name)
        desc = view.findViewById(R.id.desc)
        ratingBar = view.findViewById(R.id.rating)
        seekText = view.findViewById(R.id.seek_text)
        button = view.findViewById(R.id.edit_button)

        recipe = arguments?.getParcelable<Recipe>("recipe")!!
        recipe?.let { updateData(it) }

        button.setOnClickListener{
            if (recipe != null) {
                listener.onDataPass(recipe, "details")
            }
        }
        recipeList = RecipeJsonManager.getRecipeListFromJson(requireContext())
        newList = mutableListOf()
        deleteButton.setOnClickListener{
            showAlertDialog()
        }
    }

    private fun showAlertDialog() {
        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle("Czy na pewno chcesz usunąć przepis?")

            .setPositiveButton("Tak"){ dialog, _ ->
                if (recipe!= null){
                    var i=0
                    recipeList.forEach {
                        if (recipe.id != it.id){
                            newList.add(it)
                            it.id = i
                            i++
                        }
                    }
                    RecipeJsonManager.editRecipeList(requireContext(), newList)
                    listener.onDataPass(recipe, "add")
                }
                dialog.dismiss()
            }
            .setNegativeButton("Nie"){ dialog, _ ->
                dialog.dismiss()
            }
            .create()
        alertDialog.setOnShowListener {
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(resources.getColor(R.color.black))
            alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(resources.getColor(R.color.black))
        }

        alertDialog.show()

    }

    fun updateData(recipe: Recipe) {
        Log.i("abcd", "a")
        name.text=recipe.nazwa
        desc.text=recipe.opis
        seekText.text= "trudność: ${recipe.trudnosc}"
        ratingBar.rating = recipe.rating
    }

}

