package com.selincengiz.news.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selincengiz.news.common.DetailState
import com.selincengiz.news.common.Resource
import com.selincengiz.news.data.repo.FavoriteRepoImpl
import com.selincengiz.news.domain.entities.News
import com.selincengiz.news.domain.repo.FavoriteRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val favoriteRepoImpl: FavoriteRepo,


    ) : ViewModel() {

    private var _detailState =
        MutableStateFlow<DetailState>(DetailState.Entry)
    val detailState: StateFlow<DetailState>
        get() = _detailState.asStateFlow()


    fun isFavorite(id: String) {
        viewModelScope.launch {

            val result = favoriteRepoImpl.getFavorites()
            when (result) {
                is Resource.Success -> {
                    val res = result.data.find {
                        it.articleId == id
                    }
                    if (res == null) {
                        _detailState.value = DetailState.IsFavorite(false)
                    } else {
                        _detailState.value = DetailState.IsFavorite(true)
                    }

                }

                is Resource.Error -> {
                    _detailState.value = DetailState.Error(result.throwable)
                }
            }
        }
    }

    fun addFavorite(news: News) {
        viewModelScope.launch {
            favoriteRepoImpl.addFavorites(news)

        }
    }

    fun deleteFavorite(news: News) {
        viewModelScope.launch {
            favoriteRepoImpl.deleteFavorites(news)

        }
    }


}