package com.selincengiz.news.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selincengiz.news.common.DetailState
import com.selincengiz.news.common.FavoriteState
import com.selincengiz.news.common.Resource
import com.selincengiz.news.data.repo.FavoriteRepo
import com.selincengiz.news.data.repo.NewsRepo
import com.selincengiz.news.domain.entities.News
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteRepo: FavoriteRepo,


    ) : ViewModel() {

    private var _favoriteState =
        MutableStateFlow<FavoriteState>(FavoriteState.Entry)
    val favoriteState: StateFlow<FavoriteState>
        get() = _favoriteState.asStateFlow()


    fun getFavorites() {
        viewModelScope.launch {
            _favoriteState.value = FavoriteState.Loading
            val result = favoriteRepo.getFavorites()
            when (result) {
                is Resource.Success -> {
                    _favoriteState.value = FavoriteState.Favorite(result.data)
                }

                is Resource.Error -> {
                    _favoriteState.value = FavoriteState.Error(result.throwable)
                }
            }
        }
    }
}