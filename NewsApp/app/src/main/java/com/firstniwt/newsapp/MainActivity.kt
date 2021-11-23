package com.firstniwt.newsapp


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.firstniwt.newsapp.api.ApiRequest
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL: String = "https://content.guardianapis.com"
class MainActivity : AppCompatActivity() {

    private var TAG:String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        search_button.setOnClickListener{
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



        GlobalScope.launch(Dispatchers.IO){
            val response = api.getGuardianResponse(word).awaitResponse()
            if(response.isSuccessful){
                val data = response.body()!!
                Log.d(TAG,data.response.results[0].apiUrl)

            }

        }

    }
    private fun getUrl():String {

        return ""
    }
}