package com.firstniwt.newsapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.firstniwt.newsapp.api.ApiRequest
import com.firstniwt.newsapp.api.Data
import com.firstniwt.newsapp.api.ResponseJson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception


const val BASE_URL: String = "https://content.guardianapis.com"

class MainActivity : AppCompatActivity() {

    private var listOfTitlesAndUrls = mutableListOf<Data>()
    private var pageNumber = 1


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        search_button.setOnClickListener{
            listOfTitlesAndUrls = mutableListOf()
            getCurrentData()

        }
        load_more_button.setOnClickListener{
            pageNumber+=1
            getCurrentData()
        }


    }

    private fun getCurrentData() {
        val word = search_edit_text.text.toString()

        val api: ApiRequest = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequest::class.java)



        GlobalScope.launch(Dispatchers.IO) {
            try {

                val response = api.getGuardianResponse(word,pageNumber,10,"6fa16c5b-0cc0-44bb-8d8a-ce2b2426b262").awaitResponse()
                if (response.isSuccessful) {

                    val data = response.body()!!


                    withContext(Dispatchers.Main) {
                        extractJsonData(data)
                    }
                }

            }catch(e: Exception){
                withContext(Dispatchers.Main){

                    Toast.makeText(applicationContext,"Something went wrong, try again or check your internet connection.",Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    private fun extractJsonData(response: ResponseJson){

        for (i in 0..9){

            val singleResponse = response.response.results[i]
            val webTitle = singleResponse.webTitle
            val webUrl = singleResponse.webUrl
            val fetchedData = Data (webTitle,webUrl)

            listOfTitlesAndUrls.add(fetchedData)

        }

        val adapter = NewsAdapter(listOfTitlesAndUrls)
        main_list_view.adapter = adapter


    }



}