package com.selincengiz.news.data.entities


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("ai_region")
    val aiRegion: String?,
    @SerializedName("ai_tag")
    val aiTag: String?,
    @SerializedName("article_id")
    val articleId: String?,
    @SerializedName("category")
    val category: List<String?>?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("country")
    val country: List<String?>?,
    @SerializedName("creator")
    val creator: Any?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("keywords")
    val keywords: List<String?>?,
    @SerializedName("language")
    val language: String?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("pubDate")
    val pubDate: String?,
    @SerializedName("sentiment")
    val sentiment: String?,
    @SerializedName("sentiment_stats")
    val sentimentStats: String?,
    @SerializedName("source_icon")
    val sourceIcon: String?,
    @SerializedName("source_id")
    val sourceId: String?,
    @SerializedName("source_priority")
    val sourcePriority: Int?,
    @SerializedName("source_url")
    val sourceUrl: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("video_url")
    val videoUrl: Any?
)