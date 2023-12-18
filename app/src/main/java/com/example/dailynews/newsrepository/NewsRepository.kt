package com.example.dailynews.newsrepository

import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.dailynews.news.Article
import com.example.dailynews.news.News
import com.example.dailynews.news.NewsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository {

    suspend fun getAllNews() = NewsService.newsInstance.getHeadLines()
    suspend fun getEntertainmentNews(country:String,category:String) = NewsService.newsInstance.getCatogeryNews(country,category)
    suspend fun getSportNews(country: String,category: String) = NewsService.newsInstance.getCatogeryNews(country,category)
    suspend fun getHealthNews(country: String,category: String) = NewsService.newsInstance.getCatogeryNews(country,category)

}