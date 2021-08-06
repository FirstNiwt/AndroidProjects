package com.example.dictionaryapp

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_word_definition.*

class WordDefinitionActivity : AppCompatActivity() {
    private val defKey = "WORD_DEFINITION"
    private val secondDefKey = "SECOND_WORD_DEFINITION"
    private val idKey = "ID"
    private val twoOrMoreKey = "MoreThanOne"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_definition)


        short_def_view.text = intent.getStringExtra(defKey)
        searched_word_view.text = intent.getStringExtra(idKey)

        back_to_main_view.setOnClickListener{ finish()}
        if(intent.getBooleanExtra(twoOrMoreKey,false)) {
            alternative_definition.setVisibility(View.VISIBLE)
        }

        alternative_definition.setOnClickListener{

            if(alternative_definition.text == "SHOW ALTERNATIVE DEFINITION") {
                short_def_view.text = intent.getStringExtra(secondDefKey)
                alternative_definition.text = "SHOW MAIN DEFINITION"
                alternative_definition.setBackgroundColor(Color.BLUE)
            }

            else {
                short_def_view.text = intent.getStringExtra(defKey)
                alternative_definition.text = "SHOW ALTERNATIVE DEFINITION"
                alternative_definition.setBackgroundColor(resources.getColor(R.color.orange))

            }

        }

        }
    }
