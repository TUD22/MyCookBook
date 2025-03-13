package com.example.mycookbook

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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
        val search = findViewById<Button>(R.id.search_recipe)
        val addRecipe = findViewById<Button>(R.id.add_recipe)
        val button = findViewById<Button>(R.id.menu)
        var i =1
        button.setOnClickListener{
            if (i == 1) {
                search.visibility = View.VISIBLE
                addRecipe.visibility = View.VISIBLE

            }else{
                search.visibility = View.GONE
                addRecipe.visibility = View.GONE
            }
                i=-i;
        }
    }
}