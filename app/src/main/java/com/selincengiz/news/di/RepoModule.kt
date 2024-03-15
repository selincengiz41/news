package com.selincengiz.news.di


import com.google.firebase.auth.FirebaseAuth
import com.selincengiz.news.data.repo.AuthRepo
import com.selincengiz.news.data.repo.NewsRepo
import com.selincengiz.news.data.source.remote.NewsService

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
    fun provideAuthRepo(auth: FirebaseAuth) =
        AuthRepo(auth = auth)

    @Provides
    @Singleton
    fun provideNewsRepo(newsService: NewsService) =
        NewsRepo(newsService = newsService)



}