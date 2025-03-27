package com.example.mycookbook

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AddRecipeFragment : Fragment() {
    lateinit var name : TextView
    lateinit var ingridient: TextView
    lateinit var addIngridient: Button
    lateinit var desc : TextView
    lateinit var tag : TextView
    lateinit var seek : SeekBar
    lateinit var seekText : TextView
    lateinit var rating: RatingBar
    lateinit var recipe: Recipe
    lateinit var lista: MutableList<String>
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

        lista = mutableListOf()
        val ingredientList: RecyclerView = view.findViewById(R.id.ingridient_list)
        ingredientList.layoutManager = LinearLayoutManager(requireContext())
        val adapter = IngredientAdapter(requireContext(), lista, "add_recipe")
        ingredientList.adapter = adapter
        addIngridient = view.findViewById(R.id.add_ingridient)
        ingridient = view.findViewById(R.id.edit_ingridient)
        tag = view.findViewById(R.id.main_tag)
        name = view.findViewById(R.id.edit_name)
        desc = view.findViewById(R.id.edit_desc)
        seek = view.findViewById(R.id.seekBar)
        seekText = view.findViewById(R.id.seek_text)
        rating = view.findViewById(R.id.rating)
        seek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                seekText.text = "trudność: ${p1}"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
        addIngridient.setOnClickListener{
            if (ingridient.text.toString().isNotBlank()){
            lista.add(ingridient.text.toString())
            adapter.notifyDataSetChanged()
            ingridient.text =""}else{
                Toast.makeText(requireContext(), "Uzupełnij pole", Toast.LENGTH_SHORT).show()
            }
        }
        val recipeList = RecipeJsonManager.getRecipeListFromJson(requireContext())

        val button = view.findViewById<Button>(R.id.add_button)
        val editRecipe :Recipe? = arguments?.getParcelable("recipe")
        if (editRecipe != null) {
            tag.text = "Zaktualizuj"
            name.setText(editRecipe.nazwa)
            desc.setText(editRecipe.opis)
            seekText.text = "trudność: ${editRecipe.trudnosc}"
            seek.progress = editRecipe.trudnosc
            rating.rating = editRecipe.rating
            editRecipe.skladniki.forEach {
                lista.add(it)
            }
            adapter.notifyDataSetChanged()
            button.text = "Zaktualizuj przepis"
        }

        button.setOnClickListener {
            if (
                name.text.toString().isBlank() || desc.text.toString().isBlank() || lista.isEmpty()
            ) {
                Toast.makeText(requireContext(), "Uzupełnij pole", Toast.LENGTH_SHORT).show()
            } else {
                if (editRecipe != null) {

                    val updatedRecipe = Recipe(
                        id = editRecipe.id,
                        nazwa = name.text.toString(),
                        trudnosc = seek.progress,
                        rating = rating.rating,
                        opis = desc.text.toString(),
                        skladniki = lista
                    )

                    val index = recipeList.indexOfFirst { it.id == editRecipe.id }
                    if (index != -1) {
                        recipeList[index] = updatedRecipe
                        recipe = updatedRecipe
                    }
                    RecipeJsonManager.editRecipeList(requireContext(), recipeList)
                } else {
                    recipe = Recipe(
                        recipeList.size,
                        name.text.toString(),
                        seek.progress,
                        rating.rating,
                        desc.text.toString(),
                        lista
                    )
                    RecipeJsonManager.addRecipeToListJson(requireContext(), recipe)
                }
                listener.onDataPass(recipe, "add")
            }
        }


    }

    companion object {

    }


}