package com.selincengiz.news.domain.mapper

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
