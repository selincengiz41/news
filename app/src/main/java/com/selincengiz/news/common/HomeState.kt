package com.selincengiz.news.common

import com.selincengiz.news.domain.entities.News

sealed interface HomeState{
    object Entry : HomeState
    object Loading : HomeState
    data class Latest(val list: List<News>) : HomeState
    data class Category(val list: List<News>) : HomeState
    data class Query(val list: List<News>) : HomeState
    data class Error(val throwable: Throwable) : HomeState
}