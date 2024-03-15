package com.selincengiz.news.data.repo

import com.selincengiz.news.common.Resource
import com.selincengiz.news.data.source.remote.NewsService
import com.selincengiz.news.domain.entities.News
import com.selincengiz.news.domain.mapper.mapToNews
import java.lang.Exception

class NewsRepo(private val newsService: NewsService) {

    suspend fun getNewsByQuery(q:String,country:String): Resource<List<News>> {

        return try {
            Resource.Success(newsService.getNewsByQuery(q,country).results!!.map { result ->
                result!!.mapToNews()

            })
        } catch (e: Exception) {

            Resource.Error(e)
        }
    }

    suspend fun getNewsByLatest(country:String): Resource<List<News>> {

        return try {
            Resource.Success(newsService.getNewsByLatest(country).results!!.map { result ->
                result!!.mapToNews()

            })
        } catch (e: Exception) {

            Resource.Error(e)
        }
    }

    suspend fun getNewsByCategory(category :String,country:String): Resource<List<News>> {

        return try {
            Resource.Success(newsService.getNewsByCategory(category,country).results!!.map { result ->
                result!!.mapToNews()

            })
        } catch (e: Exception) {

            Resource.Error(e)
        }
    }


}