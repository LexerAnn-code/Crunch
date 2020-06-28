package com.ankit.crunch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.ankit.crunch.Util.HomeViewHolder
import com.ankit.crunch.databinding.HomeitemBinding

class HomeRecyclerView:androidx.recyclerview.widget.ListAdapter<NewsArticles, HomeViewHolder>(
    DIF_UTIL
) {
    companion object{
        private val DIF_UTIL:DiffUtil.ItemCallback<NewsArticles> =
            object:DiffUtil.ItemCallback<NewsArticles>(){
                override fun areItemsTheSame(
                    oldItem: NewsArticles, newItem: NewsArticles
                ): Boolean =oldItem.title==newItem.title
                override fun areContentsTheSame(
                    oldItem: NewsArticles, newItem: NewsArticles
                ): Boolean =oldItem.title==newItem.title
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
      val layoutInflater=LayoutInflater.from(parent.context)
        val binding= HomeitemBinding.inflate(layoutInflater)
        return HomeViewHolder(binding)
    }
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
      val current=getItem(position)
        holder.binding.articleNews=current
        holder.binding.executePendingBindings()
    }
}