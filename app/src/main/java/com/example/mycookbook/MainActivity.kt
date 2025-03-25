package com.example.mycookbook

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Switch
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.core.view.isGone

class MainActivity : AppCompatActivity(), DataPassInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        replaceFragment(RecipeListFragment())
        val search = findViewById<Button>(R.id.search_recipe)
        val addRecipe = findViewById<Button>(R.id.add_recipe)
        val button = findViewById<Button>(R.id.menu_button)
        val menu: LinearLayout = findViewById(R.id.menu)
        val switch: Switch = findViewById(R.id.themeSwitch)

        button.setOnClickListener {
            if (menu.isGone) {
                menu.visibility = View.VISIBLE
            } else {
                menu.visibility = View.GONE
            }
        }
        search.setOnClickListener {
            replaceFragment(RecipeListFragment())
            menu.visibility = View.GONE
        }
        addRecipe.setOnClickListener {
            replaceFragment(AddRecipeFragment())
            menu.visibility = View.GONE
        }

        val sharedPreferences = getSharedPreferences("pref", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        switch.isChecked = (sharedPreferences.getBoolean("isOn", false))
        var color =0
        if(switch.isChecked){
            color = R.color.purple
            button.setTextColor(getColor(R.color.white))
        }else{
            color = R.color.yellow
            button.setTextColor(getColor(R.color.black))
        }
        button.setBackgroundColor(getColor(color))

        switch.setOnClickListener{
            if(switch.isChecked){
                color = R.color.purple
                button.setTextColor(getColor(R.color.white))
            } else{
                color = R.color.yellow
                button.setTextColor(getColor(R.color.black))
            }
            button.setBackgroundColor(getColor(color))
            editor.putBoolean("isOn", switch.isChecked)
            editor.apply()
        }


    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDataPass(recipe: Recipe, sender: String) {
        val fragment: Fragment = when (sender) {
            "list" -> RecipeDetailsFragment()
            "add" -> RecipeListFragment()
            "details" -> AddRecipeFragment()
            else -> return
        }

        fragment.arguments = Bundle().apply {
            putParcelable("recipe", recipe)
        }

        replaceFragment(fragment)

    }
}


