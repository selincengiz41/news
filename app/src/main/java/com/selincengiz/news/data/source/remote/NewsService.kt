package com.selincengiz.news.data.source.remote

import com.selincengiz.news.common.Constants.API_KEY
import com.selincengiz.news.common.Constants.GET_NEWS
import com.selincengiz.news.data.entities.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET(GET_NEWS)
    suspend fun getNewsByQuery( @Query("q") q :String, @Query("country")country :String,@Query("apikey")api: String =API_KEY): NewsResponse

    @GET(GET_NEWS)
    suspend fun getNewsByLatest(@Query("country")country :String,@Query("apikey")api: String =API_KEY): NewsResponse

    @GET(GET_NEWS)
    suspend fun getNewsByCategory(@Query("category") category :String,@Query("country")country :String,@Query("apikey")api: String =API_KEY): NewsResponse

}