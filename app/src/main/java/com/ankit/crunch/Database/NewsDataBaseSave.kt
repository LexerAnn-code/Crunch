package com.ankit.crunch.Database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
@Entity(tableName = "news")
@Parcelize
data class NewsDataBaseSave(
    @PrimaryKey val author: String,
    val title: String
    ):Parcelable