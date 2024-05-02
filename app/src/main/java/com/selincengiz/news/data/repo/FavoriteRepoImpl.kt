package com.selincengiz.news.data.repo

import com.selincengiz.news.common.Resource
import com.selincengiz.news.data.source.local.NewsDao
import com.selincengiz.news.domain.entities.News
import com.selincengiz.news.data.mapper.mapToNews
import com.selincengiz.news.data.mapper.mapToNewsRoom
import com.selincengiz.news.domain.repo.FavoriteRepo
import java.lang.Exception

class FavoriteRepoImpl(
    private val newsDao: NewsDao,
) :FavoriteRepo {

    override suspend fun getFavorites(): Resource<List<News>> {

        return try {
            Resource.Success(newsDao.getFavorites().map { result -> result.mapToNews() })
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    override suspend fun addFavorites(news: News) {

        newsDao.addFavorites(news.mapToNewsRoom())
    }


    override suspend fun deleteFavorites(news: News) {
        newsDao.deleteFavorites(news.mapToNewsRoom())
    }
}