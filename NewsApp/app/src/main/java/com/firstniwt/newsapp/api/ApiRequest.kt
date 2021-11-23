package com.firstniwt.newsapp.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiRequest {

    @GET(value = "/{word}?page=1&page-size=10&api-key=6fa16c5b-0cc0-44bb-8d8a-ce2b2426b262")
    fun getGuardianResponse(@Path(value = "word") word:String): Call<ResponseJson>



}