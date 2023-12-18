package com.example.dailynews.news

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://newsapi.org/v2/"
const val API_KEY = "c37316ae47c74485835db05496fa7591"


// https://newsapi.org/v2/top-headlines?apiKey=c37316ae47c74485835db05496fa7591&country=us&category=entertainment
interface NewsInterface {

    @GET("top-headlines?apiKey=$API_KEY")
    suspend fun getHeadLines(
        @Query("country")
        country:String = "in"
    ):Response<News>


    @GET("top-headlines?apiKey=$API_KEY")
    suspend fun getCatogeryNews(
        @Query("country")
        country:String = "us",
        @Query("category")
        category:String
    ):Response<News>




}

object NewsService{
    val newsInstance:NewsInterface
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        newsInstance = retrofit.create(NewsInterface::class.java)
    }
}