package com.ankit.crunch.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ankit.crunch.NewsArticles
import com.ankit.crunch.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
companion object{
    const val EXTRA_POST="extra_post"
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val contentNews=intent.getParcelableExtra<NewsArticles>(EXTRA_POST)
        if(intent.hasExtra(EXTRA_POST)){
            Glide.with(news_detailed_image.context)
                .load(contentNews.urlToImage)
                .into(news_detailed_image)
        news_detailed_author_name.setText(contentNews.author)
           news_detailed_title.setText(contentNews.title)
            news_detailed_content.setText(contentNews.description)
        }


    }
}
