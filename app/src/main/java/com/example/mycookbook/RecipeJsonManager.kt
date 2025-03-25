package com.example.mycookbook

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

object RecipeJsonManager {
    private const val FILE_NAME = "recipes.json"

    fun addRecipeToListJson(context: Context, newRecipe: Recipe) {
        val gson = Gson()
        val file = File(context.filesDir, FILE_NAME)
        val json = file.readText()
        val type = object : TypeToken<MutableList<Recipe>>() {}.type
        val recipeList: MutableList<Recipe> = gson.fromJson(json, type) ?: mutableListOf()
        recipeList.add(newRecipe)

        file.writeText(gson.toJson(recipeList))
    }

    fun editRecipeList(context: Context, newRecipeList: MutableList<Recipe>) {
        val gson = Gson()
        val file = File(context.filesDir, FILE_NAME)
        file.writeText(gson.toJson(newRecipeList))
    }


    fun getRecipeListFromJson(context: Context): MutableList<Recipe> {
        val gson = Gson()
        val file = File(context.filesDir, FILE_NAME)
        if (!file.exists()) {
            file.writeText("[]")
        }
        val json = file.readText()
        val type = object : TypeToken<MutableList<Recipe>>() {}.type
        return  gson.fromJson(json, type) ?: mutableListOf()
    }
}
