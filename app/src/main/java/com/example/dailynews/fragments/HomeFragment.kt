package com.example.dailynews.fragments

import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dailynews.R
import com.example.dailynews.adapter.NewsAdapter
import com.example.dailynews.databinding.FragmentHomeBinding
import com.example.dailynews.news.Article
import com.example.dailynews.news.News
import com.example.dailynews.news.NewsService
import com.example.dailynews.newsrepository.NewsRepository
import com.example.dailynews.viewmodel.NewsApplication
import com.example.dailynews.viewmodel.NewsViewModel
import com.example.dailynews.viewmodel.NewsViewModelFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    private lateinit var binding:FragmentHomeBinding
    private lateinit var adapter: NewsAdapter
    private val viewModel: NewsViewModel by activityViewModels {
        NewsViewModelFactory(
            (activity?.application as NewsApplication).repo
        )
    }


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

        binding.all.setOnClickListener{
            getNews()
        }
        binding.entertainment.setOnClickListener{
            viewModel.entertainmentNews.observe(viewLifecycleOwner, Observer {
                adapter.submitList(it.articles)
            })

        }

    }

    private fun getNews() {
        viewModel.allNews.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it.articles)
        })
    }

}