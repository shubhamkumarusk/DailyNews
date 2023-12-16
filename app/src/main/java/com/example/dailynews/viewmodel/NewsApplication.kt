package com.example.dailynews.viewmodel

import android.app.Application
import com.example.dailynews.newsrepository.NewsRepository

class NewsApplication:Application() {
    val repo = NewsRepository()
}