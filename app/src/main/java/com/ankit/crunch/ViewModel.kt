package com.ankit.crunch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankit.crunch.Repository.Repository
import com.ankit.crunch.Util.LoadingState
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
fun saveNewsLive():LiveData<List<NewsArticles>>{
        return repository.getNews()
    }
    fun saveNewsLive2():LiveData<List<NewsArticles2>>{
        return repository.getNews_2()
    }

private val _loadingState=MutableLiveData<LoadingState>()
    val loadingState:LiveData<LoadingState> get()=_loadingState


    private val _data = MutableLiveData<MutableList<NewsArticles>>()
    val data: LiveData<MutableList<NewsArticles>> get() = _data

    private val _datas = MutableLiveData<MutableList<NewsArticles2>>()
    val datas: LiveData<MutableList<NewsArticles2>> get() = _datas


  suspend  fun saveNews(newsArticles: NewsArticles) {
        viewModelScope.launch {
            repository.insertNewsData(newsArticles)
        }
    }
    suspend  fun saveNews_2(newsArticles2: NewsArticles2) {
        viewModelScope.launch {
            repository.insertNewsData_2(newsArticles2)
        }
    }
    fun fetchData() {
        viewModelScope.launch {
            try {
                _loadingState.value= LoadingState.LODING
                val news = newsApiRepository.getNews()
                val newsSeocnd=newsApiRepository.getNewsSecond()
                _data.postValue(news.articles)
                _datas.postValue(newsSeocnd.articles)
                _loadingState.value= LoadingState.LOADED
                debugger("Success->>${newsApiRepository.getNews().articles}")
                debugger("SecondNews->>${newsApiRepository.getNewsSecond().articles}")
            } catch (e: Exception) {
                debugger("Error->>${e.localizedMessage}")

            }
        }
    }
}