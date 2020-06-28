package com.ankit.crunch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankit.crunch.Repository.Repository
import com.example.newsfinishedapp.Network.NewsApiRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class ViewModel(
    private val repository: Repository,
    private val newsApiRepository: NewsApiRepository
) : ViewModel() {
    init {
        fetchData()
        debugger("Reading")
    }

    private val _data = MutableLiveData<MutableList<NewsArticles>>()
    val data: LiveData<MutableList<NewsArticles>> get() = _data

    private val _datas = MutableLiveData<MutableList<NewsArticles2>>()
    val datas: LiveData<MutableList<NewsArticles2>> get() = _datas


    fun saveNews(newsArticles: NewsArticles) {
        viewModelScope.launch {
            repository.insertNewsData(newsArticles)
        }
    }

    fun fetchData() {
        viewModelScope.launch {
            try {
                val news = newsApiRepository.getNews()
                val newsSeocnd=newsApiRepository.getNewsSecond()
                _data.postValue(news.articles)
                _datas.postValue(newsSeocnd.articles)
                debugger("Success->>${newsApiRepository.getNews().articles}")
                debugger("SecondNews->>${newsApiRepository.getNewsSecond().articles}")
            } catch (e: Exception) {
                debugger("Error->>${e.localizedMessage}")

            }
        }
    }
}