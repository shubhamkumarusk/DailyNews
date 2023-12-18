package com.example.dailynews.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dailynews.R
import com.example.dailynews.databinding.FragmentHomeBinding
import com.example.dailynews.databinding.NewsListBinding
import com.example.dailynews.news.Article

class NewsAdapter(private val OnNewsClikced:(Article)->Unit):ListAdapter<Article,NewsAdapter.NewsViewHolder>(DiffCallBack){
    companion object {
            private val DiffCallBack = object :DiffUtil.ItemCallback<Article>(){

                override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                    return oldItem.url==newItem.url
                }

                override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                    return oldItem==newItem
                }

            }
    }

    class NewsViewHolder(private val binding: NewsListBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(article: Article){
            binding.newsTitle.text = article.title
            binding.newsAuthor.text = article.author
            binding.newsDescription.text = article.description
            val imageUrl = article.urlToImage
            binding.newsImage.let {
                Glide.with(itemView.context)
                    .load(imageUrl)
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.connection_error)
                    .into(binding.newsImage)
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val viewHolder = NewsViewHolder(
            NewsListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
        return viewHolder
    }


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val curr = getItem(position)
        holder.bind(curr)
        holder.itemView.setOnClickListener{
            OnNewsClikced(curr)
        }

    }


}


