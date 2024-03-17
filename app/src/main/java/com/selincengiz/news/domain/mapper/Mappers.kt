package com.selincengiz.news.domain.mapper

import com.selincengiz.news.data.entities.NewsRoom
import com.selincengiz.news.data.entities.Result
import com.selincengiz.news.domain.entities.News

fun Result.mapToNews(): News {
    return News(
        articleId,
        category,
        content,
        country,
        description,
        imageUrl,
        keywords,
        language,
        link,
        pubDate,
        sentiment,
        sentimentStats,
        sourceIcon,
        sourceId,
        sourcePriority,
        sourceUrl,
        title,
    )
}


fun NewsRoom.mapToNews(): News {
    return News(
        articleId,
        listOf(category),
        content,
        listOf(country),
        description,
        imageUrl,
        listOf(keywords),
        language,
        link,
        pubDate,
        sentiment,
        sentimentStats,
        sourceIcon,
        sourceId,
        sourcePriority,
        sourceUrl,
        title,
    )
}

fun News.mapToNewsRoom(): NewsRoom {
    return NewsRoom(
        articleId!!,
        category?.get(0),
        content,
        country?.get(0),
        description,
        imageUrl,
        keywords?.get(0),
        language,
        link,
        pubDate,
        sentiment,
        sentimentStats,
        sourceIcon,
        sourceId,
        sourcePriority,
        sourceUrl,
        title,
    )
}
