package com.ankit.crunch.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.util.Linkify
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.ankit.crunch.NewsArticles
import com.ankit.crunch.NewsArticles2
import com.ankit.crunch.R
import com.ankit.crunch.debugger
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail_2.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity_2 : AppCompatActivity() {
    companion object {
        const val EXTRA_POSTS = "extra_post"
    }
    private val detailedViewModel_2 by viewModel<com.ankit.crunch.ViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_2)
        val contentNews = intent.getParcelableExtra<NewsArticles2>(EXTRA_POSTS)
        if (intent.hasExtra(EXTRA_POSTS)) {
            Glide.with(news_detailed_image_2.context)
                .load(contentNews.urlToImage)
                .into(news_detailed_image_2)
            news_detailed_author_name_2.setText(contentNews.author)
            news_detailed_title_2.setText(contentNews.title)
            news_detailed_content_2.setText(contentNews.description)
            read_2.setText(contentNews.url)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.topbarmenu, menu)
        return true
    }

    override  fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.save -> {
            debugger("Clicked on Saved")
            Toast.makeText(this,"Saved", Toast.LENGTH_LONG).show()
            val contentNews = intent.getParcelableExtra<NewsArticles2>(DetailActivity_2.EXTRA_POSTS)
            if (intent.hasExtra(DetailActivity_2.EXTRA_POSTS)) {
                GlobalScope.launch {
                    detailedViewModel_2.saveNews_2(contentNews)
                }

            }
            true
        }
        R.id.share -> {
            Toast.makeText(this,"Share", Toast.LENGTH_LONG).show()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}
