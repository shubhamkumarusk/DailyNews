package com.example.dailynews.news

data class News (
    val totalResults:Int,
    val articles:MutableList<Article>
)