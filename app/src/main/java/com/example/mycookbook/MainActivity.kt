package com.example.mycookbook

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {


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
        val menu : LinearLayout = findViewById(R.id.menu)
        var i =1
        button.setOnClickListener{
            if (i == 1) {
                menu.visibility = View.VISIBLE
            }else{
                menu.visibility = View.GONE
            }
                i=-i;
        }
        search.setOnClickListener{
            replaceFragment(RecipeListFragment())
        }
        addRecipe.setOnClickListener{
            replaceFragment(AddRecipeFragment())
        }

    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment, fragment)
            .addToBackStack(null)
            .commit()
    }
}