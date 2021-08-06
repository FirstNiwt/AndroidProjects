package com.example.dictionaryapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    private val defKey = "WORD_DEFINITION"
    private val secondDefKey = "SECOND_WORD_DEFINITION"
    private val idKey = "ID"
    private val twoOrMoreKey = "MoreThanOne"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val queue = Volley.newRequestQueue(this)
        search_button.setOnClickListener {

            val word = word_searched.text.toString()
            val apiKey = "d7fbdb12-bfb0-455d-8d9f-0a5c9e0cd871"
            val url =
                "https://www.dictionaryapi.com/api/v3/references/learners/json/$word?key=$apiKey"

            val stringRequest = StringRequest(
                Request.Method.GET,
                url,
                {
                                  response ->
                    try {
                        extractDefinitionFromJson(response,word)
                    } catch (exception:Exception){
                        Toast.makeText(this,"Word not in dictionary, enter a valid word!",Toast.LENGTH_SHORT).show()

                    }
                },
                {

                        error ->
                    Toast.makeText(this,error.message,Toast.LENGTH_SHORT).show()
                })

            queue.add(stringRequest)
        }
    }

    private fun extractDefinitionFromJson(response: String,text:String){
        var moreThanOneDef = false
        val jsonArray = JSONArray(response)

        val firstIndex = jsonArray.getJSONObject(0)
        val arrayOfShortDef = firstIndex.getJSONArray("shortdef")
        val shortDef = arrayOfShortDef.get(0)
        var alternateDefinition = ""

        if(arrayOfShortDef.length()>1) {
            moreThanOneDef = true
            alternateDefinition = arrayOfShortDef.get(1).toString()
        }

        val intent = Intent(this, WordDefinitionActivity::class.java)
        intent.putExtra(defKey,shortDef.toString())
        intent.putExtra(idKey, text)
        intent.putExtra(twoOrMoreKey,moreThanOneDef)
        intent.putExtra(secondDefKey,alternateDefinition)
        startActivity(intent)




    }
}