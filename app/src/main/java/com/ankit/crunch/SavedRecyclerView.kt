package com.ankit.crunch

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.ankit.crunch.Ui.DetailActivity
import com.ankit.crunch.Util.SavedViewHolder
import com.ankit.crunch.databinding.SaveditemBinding

class SavedRecyclerView (val host: Activity):ListAdapter<NewsArticles, SavedViewHolder>(
    DIFF_UTIL
){
    companion object{
        private val DIFF_UTIL: DiffUtil.ItemCallback<NewsArticles> =
            object: DiffUtil.ItemCallback<NewsArticles>(){
                override fun areItemsTheSame(
                    oldItem: NewsArticles, newItem: NewsArticles
                ): Boolean =oldItem.title==newItem.title
                override fun areContentsTheSame(
                    oldItem: NewsArticles, newItem: NewsArticles
                ): Boolean =oldItem.title==newItem.title
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val binding=SaveditemBinding.inflate(layoutInflater)
        return SavedViewHolder(binding)
    }


    override fun onBindViewHolder(holder: SavedViewHolder, position: Int) {
      val current=getItem(position)
        holder.binding.savedNews=current
        holder.binding.executePendingBindings()
        holder.itemView.setOnClickListener {
            host.startActivity(
                Intent(host, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.EXTRA_POST,current)
                }
            )
        }


    }
}