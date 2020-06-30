package com.ankit.crunch

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "news")
@Parcelize
data class NewsArticles(
    @PrimaryKey(autoGenerate = true)
    var id:Long=0,
    @SerializedName("author")
    @Expose
    val author: String,
    @SerializedName("title")
    @Expose
    val title: String,
    @SerializedName("description")
    @Expose
    val description: String,
    @SerializedName("url")
    @Expose
    val url: String,
    @SerializedName("urlToImage")
    @Expose
    val urlToImage: String
) : Parcelable

