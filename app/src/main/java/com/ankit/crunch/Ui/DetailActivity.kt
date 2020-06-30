package com.ankit.crunch.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.util.Linkify
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
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

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_POST = "extra_post"

    }

    private val detailedViewModel by viewModel<com.ankit.crunch.ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val contentNews = intent.getParcelableExtra<NewsArticles>(EXTRA_POST)

        if (intent.hasExtra(EXTRA_POST)) {
            Glide.with(news_detailed_image.context)
                .load(contentNews.urlToImage)
                .into(news_detailed_image)
            news_detailed_author_name.setText(contentNews.author)
            news_detailed_title.setText(contentNews.title)
            news_detailed_content.setText(contentNews.description)
            val url = contentNews.url
            read.setText(contentNews.url)
            Linkify.addLinks(read, Linkify.WEB_URLS)
        }

    }
    fun loadNewsDetailed(){
        val contentNews = intent.getParcelableExtra<NewsArticles>(EXTRA_POST)
        if (intent.hasExtra(EXTRA_POST)) {
            Glide.with(news_detailed_image.context)
                .load(contentNews.urlToImage)
                .into(news_detailed_image)
            news_detailed_author_name.setText(contentNews.author)
            news_detailed_title.setText(contentNews.title)
            news_detailed_content.setText(contentNews.description)
            val url = contentNews.url
            read.setText(contentNews.url)
            Linkify.addLinks(read, Linkify.WEB_URLS)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.topbarmenu, menu)
        return true
    }

    override  fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.save -> {
            debugger("Clicked on Saved")
            Toast.makeText(this,"Saved",Toast.LENGTH_LONG).show()
            val contentNews = intent.getParcelableExtra<NewsArticles>(EXTRA_POST)
            if (intent.hasExtra(EXTRA_POST)) {
                GlobalScope.launch {
                    detailedViewModel.saveNews(contentNews)
                }

            }
            true
        }
        R.id.share -> {
            Toast.makeText(this,"Share",Toast.LENGTH_LONG).show()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}




