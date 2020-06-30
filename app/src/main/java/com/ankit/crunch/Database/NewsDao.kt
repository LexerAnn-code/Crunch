package com.ankit.crunch.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ankit.crunch.NewsArticles
import com.ankit.crunch.NewsArticles2

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend  fun insertNews(newsArticles: NewsArticles)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun insertNews_2(newsArticles2: NewsArticles2)


    @Query("SELECT * FROM news ORDER BY id DESC")
  fun savedNews():LiveData<List<NewsArticles>>

    @Query("SELECT * FROM news ORDER BY id DESC")
    fun savedNews_2():LiveData<List<NewsArticles2>>
}