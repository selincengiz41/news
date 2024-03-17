package com.selincengiz.news.common

import com.selincengiz.news.domain.entities.News

sealed interface FavoriteState{
    object Entry : FavoriteState
    object Loading : FavoriteState
    data class Favorite(val list: List<News>) : FavoriteState
    data class Error(val throwable: Throwable) : FavoriteState
}