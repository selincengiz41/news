package com.selincengiz.news.domain.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val articleId: String?,
    val category: List<String?>?,
    val content: String?,
    val country: List<String?>?,
    val description: String?,
    val imageUrl: String?,
    val keywords: List<String?>?,
    val language: String?,
    val link: String?,
    val pubDate: String?,
    val sentiment: String?,
    val sentimentStats: String?,
    val sourceIcon: String?,
    val sourceId: String?,
    val sourcePriority: Int?,
    val sourceUrl: String?,
    val title: String?,
):Parcelable
