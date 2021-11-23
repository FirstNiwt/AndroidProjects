package com.firstniwt.newsapp.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiRequest {

    @GET(value = "/{word}")
    fun getGuardianResponse(
        @Path(value = "word") word: String,
        @Query("page") numberOfPage: Int,
        @Query("page-size") pageSize: Int,
        @Query("api-key") apiKey: String
    ): Call<ResponseJson>


}