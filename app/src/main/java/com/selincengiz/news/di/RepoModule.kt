package com.selincengiz.news.di


import com.google.firebase.auth.FirebaseAuth
import com.selincengiz.news.data.repo.AuthRepoImpl
import com.selincengiz.news.data.repo.FavoriteRepoImpl
import com.selincengiz.news.data.repo.NewsRepoImpl
import com.selincengiz.news.data.source.local.NewsDao
import com.selincengiz.news.data.source.remote.NewsService
import com.selincengiz.news.domain.repo.AuthRepo
import com.selincengiz.news.domain.repo.FavoriteRepo
import com.selincengiz.news.domain.repo.NewsRepo

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {


    @Provides
    @Singleton
    fun provideAuthRepo(auth: FirebaseAuth): AuthRepo =
        AuthRepoImpl(auth = auth)

    @Provides
    @Singleton
    fun provideNewsRepo(newsService: NewsService): NewsRepo =
        NewsRepoImpl(newsService = newsService)

    @Provides
    @Singleton
    fun provideFavoriteRepo(newsDao: NewsDao): FavoriteRepo =
        FavoriteRepoImpl(newsDao = newsDao)

}