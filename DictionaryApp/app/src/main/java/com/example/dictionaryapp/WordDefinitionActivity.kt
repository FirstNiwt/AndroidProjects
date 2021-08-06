package com.example.dictionaryapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_word_definition.*

class WordDefinitionActivity : AppCompatActivity() {
    private val key = "WORD_DEFINITION"
    private val idKey = "ID"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_definition)

        intent.getStringExtra(key)
        short_def_view.text = intent.getStringExtra(key)
        searched_word_view.text = intent.getStringExtra(idKey)

        back_to_main_view.setOnClickListener{ finish()}
    }
}