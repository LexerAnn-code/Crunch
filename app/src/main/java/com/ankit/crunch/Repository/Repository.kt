package com.ankit.crunch.Repository

import androidx.lifecycle.LiveData
import com.ankit.crunch.Database.NewsDao
import com.ankit.crunch.NewsArticles
import com.ankit.crunch.NewsArticles2
import com.ankit.crunch.debugger
import com.example.newsfinishedapp.Network.NewsApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val newsApiRepository: NewsApiRepository,private val newsDao: NewsDao) {
    fun getNews():LiveData<List<NewsArticles>> {
       return newsDao.savedNews()
    }
    fun getNews_2():LiveData<List<NewsArticles2>> {
        return newsDao.savedNews_2()
    }
        suspend fun insertNewsData(newsArticles: NewsArticles) {
            withContext(Dispatchers.IO) {
                debugger("Data Saved->>>>>")
                newsDao.insertNews(newsArticles)
            }
        }
    suspend fun insertNewsData_2(newsArticles2: NewsArticles2) {
        withContext(Dispatchers.IO) {
            debugger("Data Saved->>>>>")
            newsDao.insertNews_2(newsArticles2)
        }
    }

//            suspend fun callNews():List<com.ankit.crunch.NewsArticles>{
//                val newsALL=newsApiRepository.getNews()
//                val listy=newsALL.articles
//                return listy
//                debugger("News->> Data")
//
//            }


    }
