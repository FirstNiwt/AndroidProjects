package com.example.dictionaryapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val queue = Volley.newRequestQueue(this)
        search_button.setOnClickListener {

            val word = word_searched.text
            val apiKey = "d7fbdb12-bfb0-455d-8d9f-0a5c9e0cd871"
            val url =
                "https://www.dictionaryapi.com/api/v3/references/learners/json/$word?key=$apiKey"

            val stringRequest = StringRequest(
                Request.Method.GET,
                url,
                Response.Listener {
                                  response ->
                    response
                },
                Response.ErrorListener {

                        error ->
                    error
                })

            queue.add(stringRequest)
        }
    }
}