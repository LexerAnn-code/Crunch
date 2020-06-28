package com.ankit.crunch.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ankit.crunch.NewsArticles
@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertNews(newsArticles: NewsArticles)

    @Query("SELECT * FROM news")

    fun savedNews():LiveData<List<NewsArticles>>
}