package com.example.dailynews.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dailynews.R
import com.example.dailynews.adapter.NewsAdapter
import com.example.dailynews.databinding.FragmentHomeBinding
import com.example.dailynews.news.Article
import com.example.dailynews.news.News
import com.example.dailynews.news.NewsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    private lateinit var binding:FragmentHomeBinding
    private lateinit var adapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = NewsAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        getNews()


    }

    private fun getNews() {
        val news = NewsService.newsInstance.getHeadLines("us")
        news.enqueue(object :Callback<News>{

            override fun onResponse(call: Call<News>, response: Response<News>) {
                val newsBody = response.body()
                if(news!=null){
                    if (newsBody != null) {
                        adapter.submitList(newsBody.articles)
                    }
                }
            }


            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.e("Error!!", "Unsuccessful response: ${t.message}")
            }

        })
    }


}