package com.selincengiz.news.domain.repo

import com.selincengiz.news.common.Resource
import com.selincengiz.news.domain.entities.News

interface NewsRepo {

    suspend fun getNewsByQuery(q: String, country: String): Resource<List<News>>

    suspend fun getNewsByLatest(country: String): Resource<List<News>>

    suspend fun getNewsByCategory(category: String, country: String): Resource<List<News>>
}