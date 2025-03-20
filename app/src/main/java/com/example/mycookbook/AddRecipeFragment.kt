package com.example.mycookbook

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
    lateinit var seek : SeekBar
    lateinit var seekText : TextView
    lateinit var rating: RatingBar

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

        name = view.findViewById(R.id.edit_name)
        desc = view.findViewById(R.id.edit_desc)
        seek = view.findViewById(R.id.seekBar)
        seekText = view.findViewById(R.id.seek_text)
        var seekInt = 1
        rating = view.findViewById(R.id.rating)
        var recipe: Recipe
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

        val button = view.findViewById<Button>(R.id.add_button)
        button.setOnClickListener{
            recipe = Recipe(name.text.toString(), seekInt, rating.rating, desc.text.toString())
        }
    }

    companion object {

    }
}