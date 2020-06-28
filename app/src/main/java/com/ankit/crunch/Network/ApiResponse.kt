package com.example.newsfinishedapp.Network

import android.os.Parcelable
import com.ankit.crunch.NewsArticles
import com.ankit.crunch.NewsArticles2
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ApiResponse(
    @SerializedName("status")
    @Expose
    val status: String,
    @SerializedName("totalResults")
    @Expose
    val totalResponse: String,
    @SerializedName("articles")
    @Expose
    val articles: MutableList<NewsArticles>
) : Parcelable


@Parcelize
data class _ApiResponse(
    @SerializedName("status")
    @Expose
    val status: String,
    @SerializedName("totalResults")
    @Expose
    val totalResponse: String,
    @SerializedName("articles")
    @Expose
    val articles: MutableList<NewsArticles2>
) : Parcelable

