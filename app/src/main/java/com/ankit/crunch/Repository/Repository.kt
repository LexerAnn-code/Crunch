package com.ankit.crunch.Repository

import androidx.lifecycle.LiveData
import com.ankit.crunch.Database.NewsDao
import com.ankit.crunch.NewsArticles
import com.example.newsfinishedapp.Network.NewsApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val newsApiRepository: NewsApiRepository,private val newsDao: NewsDao) {
    private lateinit var allSaveData:LiveData<List<NewsArticles>>
    suspend fun getNews() {
        withContext(Dispatchers.IO) {
          val data= newsDao.savedNews()
            return@withContext data
        }
    }
        suspend fun insertNewsData(newsArticles: NewsArticles) {
            withContext(Dispatchers.IO) {
                newsDao.insertNews(newsArticles)
            }
            suspend fun getAllNewsSaved() {
                return getNews()
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
