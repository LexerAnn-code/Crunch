package com.ankit.crunch

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.ankit.crunch.Ui.DetailActivity
import com.ankit.crunch.Ui.DetailActivity_2
import com.ankit.crunch.Util.NewsSecondViewHolder
import com.ankit.crunch.databinding.NewscardBinding

class NewsSecondRecycler(val host:Activity):androidx.recyclerview.widget.ListAdapter<NewsArticles2,NewsSecondViewHolder>(DIFF_UTIL) {
    companion object{
        private val DIFF_UTIL:DiffUtil.ItemCallback<NewsArticles2> =
                object :DiffUtil.ItemCallback<NewsArticles2>(){
                    override fun areItemsTheSame(
                        oldItem: NewsArticles2,
                        newItem: NewsArticles2
                    ): Boolean =newItem.title==oldItem.title

                    override fun areContentsTheSame(
                        oldItem: NewsArticles2,
                        newItem: NewsArticles2
                    ): Boolean=newItem.title==oldItem.title
                }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsSecondViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        val binding= NewscardBinding.inflate(layoutInflater)
        return NewsSecondViewHolder(binding)
    }
    override fun onBindViewHolder(holder: NewsSecondViewHolder, position: Int) {
        val current=getItem(position)
        holder.binding.articleNewsSecond=current
        holder.binding.executePendingBindings()
        holder.itemView.setOnClickListener {
            debugger("Recycler")
            host.startActivity(
                Intent(host, DetailActivity_2::class.java).apply {
                    putExtra(DetailActivity_2.EXTRA_POSTS,current)
                }
            )
        }

    }
}