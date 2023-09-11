package com.example.practise2023.data.remote

import com.example.practise2023.data.remote.dto.NewsResponse
import com.example.practise2023.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("everything")
    suspend fun getNews(
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse


    @GET("top-headlines")
    suspend fun getNewsByCategory(
        @Query("category") searchQuery: String,
        @Query("country") country:String = "us",
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse
}