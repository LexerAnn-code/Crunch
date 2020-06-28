package com.example.newsfinishedapp.Network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiRepository{
    companion object{
        const val API_KEY="91b2475438934ab4a7cd53dc42f8da51"
        const val domains_="techcrunch.com,thenextweb.com"
        const val q_="apple"
        const val from_="from=2020-06-26"
        const val to_="to=2020-06-26"
        const val sort_="sortBy=popularity"
        const val sources_="bbc-news"



    }
    @GET("/v2/everything")
    suspend fun getNews(
        @Query("domains")dom:String=domains_,
        @Query("apiKey")key:String=API_KEY

    ):ApiResponse

    @GET("/v2/top-headlines")
//    suspend fun getNewsSecond(
//        @Query("q")dom:String=q_,
//        @Query("from")from:String=from_,
//        @Query("to")to:String=to_,
//        @Query("sortBy")sortBy:String=sort_,
//        @Query("apiKey")key:String=API_KEY
//    ):_ApiResponse
//
    suspend fun getNewsSecond(
        @Query("sources")sortBy:String= sources_,
        @Query("apiKey")key:String=API_KEY
    ):_ApiResponse


}