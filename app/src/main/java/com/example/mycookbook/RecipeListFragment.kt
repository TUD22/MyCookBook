package com.example.mycookbook

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class RecipeListFragment : Fragment() {
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recipeList = listOf(
            Recipe("Pizza", 2, 2f, "a"),
            Recipe("Lasagna", 4, 5f, "b"),
            Recipe("Spaghetti", 6, 1f, "d")
        )
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = RecipeAdapter(recipeList){ recipe ->
            listener.onDataPass(recipe, "list")
        }
    }


    companion object {

    }
}