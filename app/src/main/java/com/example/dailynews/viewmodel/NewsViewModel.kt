package com.example.dailynews.viewmodel

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.engine.Resource
import com.example.dailynews.news.Article
import com.example.dailynews.news.News
import com.example.dailynews.news.NewsService
import com.example.dailynews.newsrepository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel(private val newsRepository: NewsRepository):ViewModel() {
    val country = "in"
    val allNews = MutableLiveData<News>()
    val entertainmentNews = MutableLiveData<News>()
    val sportNews = MutableLiveData<News>()
    val healthNews = MutableLiveData<News>()
    val businessNews = MutableLiveData<News>()
    val techNews = MutableLiveData<News>()
    val scienceNews = MutableLiveData<News>()

    init {
        getAllNews()
        getEntertainmentNews()
        getSportNews()
        getHealthNews()
        getBusinessNews()
        getTechNews()
        getScienceNews()
    }
    fun getAllNews() = viewModelScope.launch(Dispatchers.IO) {
        val response = newsRepository.getAllNews()
        allNews.postValue(response.body())
    }
    fun getEntertainmentNews() = viewModelScope.launch(Dispatchers.IO) {
        val response = newsRepository.getEntertainmentNews(country,"entertainment")
        entertainmentNews.postValue(response.body())
    }
    fun getSportNews() = viewModelScope.launch(Dispatchers.IO) {
        val response = NewsService.newsInstance.getCatogeryNews(country,"sport")
        sportNews.postValue(response.body())
    }
    fun getHealthNews() = viewModelScope.launch(Dispatchers.IO) {
        val response = NewsService.newsInstance.getCatogeryNews(country,"health")
        healthNews.postValue(response.body())

    }
    fun getBusinessNews() = viewModelScope.launch(Dispatchers.IO) {
        val response = NewsService.newsInstance.getCatogeryNews(country,"business")
        businessNews.postValue(response.body())

    }
    fun getTechNews() = viewModelScope.launch(Dispatchers.IO) {
        val response = NewsService.newsInstance.getCatogeryNews(country,"technology")
        techNews.postValue(response.body())

    }
    fun getScienceNews() = viewModelScope.launch(Dispatchers.IO) {
        val response = NewsService.newsInstance.getCatogeryNews(country,"science")
        scienceNews.postValue(response.body())

    }






}


class NewsViewModelFactory(private val newsRepository: NewsRepository ):ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(NewsViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return NewsViewModel(newsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}