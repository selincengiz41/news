package com.selincengiz.news.data.repo

import com.selincengiz.news.common.Resource
import com.selincengiz.news.data.entities.NewsRoom
import com.selincengiz.news.data.source.local.NewsDao
import com.selincengiz.news.domain.entities.News
import com.selincengiz.news.domain.mapper.mapToNews
import com.selincengiz.news.domain.mapper.mapToNewsRoom
import java.lang.Exception

class FavoriteRepo(
    private val newsDao: NewsDao,
) {

    suspend fun getFavorites(): Resource<List<News>> {

        return try {
            Resource.Success(newsDao.getFavorites().map { result -> result.mapToNews() })
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    suspend fun addFavorites(news: News) {

        newsDao.addFavorites(news.mapToNewsRoom())
    }


    suspend fun deleteFavorites(news: News) {
        newsDao.deleteFavorites(news.mapToNewsRoom())
    }
}