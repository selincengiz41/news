package com.selincengiz.news.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_favorite")
data class NewsRoom(
    @PrimaryKey
    @ColumnInfo("articleId")
    val articleId: String,
    @ColumnInfo("category")
    val category: String?,
    @ColumnInfo("content")
    val content: String?,
    @ColumnInfo("country")
    val country: String?,
    @ColumnInfo("description")
    val description: String?,
    @ColumnInfo("imageUrl")
    val imageUrl: String?,
    @ColumnInfo("keywords")
    val keywords: String?,
    @ColumnInfo("language")
    val language: String?,
    @ColumnInfo("link")
    val link: String?,
    @ColumnInfo("pubDate")
    val pubDate: String?,
    @ColumnInfo("sentiment")
    val sentiment: String?,
    @ColumnInfo("sentimentStats")
    val sentimentStats: String?,
    @ColumnInfo("sourceIcon")
    val sourceIcon: String?,
    @ColumnInfo("sourceId")
    val sourceId: String?,
    @ColumnInfo("sourcePriority")
    val sourcePriority: Int?,
    @ColumnInfo("sourceUrl")
    val sourceUrl: String?,
    @ColumnInfo("title")
    val title: String?,

    )
