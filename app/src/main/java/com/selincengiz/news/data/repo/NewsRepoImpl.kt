package com.selincengiz.news.data.repo

import com.selincengiz.news.common.Resource
import com.selincengiz.news.data.source.remote.NewsService
import com.selincengiz.news.domain.entities.News
import com.selincengiz.news.data.mapper.mapToNews
import com.selincengiz.news.domain.repo.NewsRepo
import java.lang.Exception

class NewsRepoImpl(private val newsService: NewsService) :NewsRepo{

    override suspend fun getNewsByQuery(q:String, country:String): Resource<List<News>> {

        return try {
            Resource.Success(newsService.getNewsByQuery(q,country).results!!.map { result ->
                result!!.mapToNews()

            })
        } catch (e: Exception) {

            Resource.Error(e)
        }
    }

    override suspend fun getNewsByLatest(country:String): Resource<List<News>> {

        return try {
            Resource.Success(newsService.getNewsByLatest(country).results!!.map { result ->
                result!!.mapToNews()

            })
        } catch (e: Exception) {

            Resource.Error(e)
        }
    }

    override suspend fun getNewsByCategory(category :String, country:String): Resource<List<News>> {

        return try {
            Resource.Success(newsService.getNewsByCategory(category,country).results!!.map { result ->
                result!!.mapToNews()

            })
        } catch (e: Exception) {

            Resource.Error(e)
        }
    }


}