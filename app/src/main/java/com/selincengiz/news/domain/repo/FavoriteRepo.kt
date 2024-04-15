package com.selincengiz.news.domain.repo

import com.selincengiz.news.common.Resource
import com.selincengiz.news.domain.entities.News

interface FavoriteRepo {

    suspend fun getFavorites(): Resource<List<News>>

    suspend fun addFavorites(news: News)

    suspend fun deleteFavorites(news: News)
}