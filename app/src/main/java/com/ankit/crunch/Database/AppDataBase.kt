package com.ankit.crunch.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ankit.crunch.NewsArticles
import com.ankit.crunch.NewsArticles2


@Database(entities = [NewsArticles::class, NewsArticles2::class],version = 4,exportSchema = false)
abstract class AppDataBase :RoomDatabase() {
    abstract val newsDao:NewsDao
}