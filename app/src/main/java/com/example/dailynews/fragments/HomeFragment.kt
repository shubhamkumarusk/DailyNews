package com.example.dailynews.fragments

import android.net.Uri
import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.browser.customtabs.CustomTabsIntent
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
        adapter = NewsAdapter({
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(requireContext(), Uri.parse(it.url))
        })

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        getNews()


        //All News
        binding.all.setOnClickListener{
            getNews()
        }


        //Entertainment
        binding.entertainment.setOnClickListener{
            viewModel.entertainmentNews.observe(viewLifecycleOwner, Observer {
                adapter.submitList(it.articles)
            })

        }



        //Sport
        binding.sport.setOnClickListener{
            viewModel.sportNews.observe(viewLifecycleOwner, Observer {
                adapter.submitList(it.articles)
            })
        }

        //Health
        binding.health.setOnClickListener{
            viewModel.healthNews.observe(viewLifecycleOwner, Observer {
                adapter.submitList(it.articles)
            })
        }

        //Business
        binding.business.setOnClickListener{
            viewModel.businessNews.observe(viewLifecycleOwner, Observer {
                adapter.submitList(it.articles)
            })
        }

        //Technology
        binding.tech.setOnClickListener{
            viewModel.techNews.observe(viewLifecycleOwner, Observer {
                adapter.submitList(it.articles)
            })
        }

        //Science
        binding.science.setOnClickListener{
            viewModel.scienceNews.observe(viewLifecycleOwner, Observer {
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