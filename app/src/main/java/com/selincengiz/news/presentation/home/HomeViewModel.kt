package com.selincengiz.news.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selincengiz.news.common.HomeState
import com.selincengiz.news.common.Resource
import com.selincengiz.news.data.repo.NewsRepoImpl
import com.selincengiz.news.domain.repo.NewsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val newsRepoImpl: NewsRepo) : ViewModel() {

    private var _homeState = MutableStateFlow<HomeState>(HomeState.Entry)
    val homeState: StateFlow<HomeState>
        get() = _homeState.asStateFlow()

    fun getNewsByLatest(country: String) {
        viewModelScope.launch {
            _homeState.value = HomeState.Loading
            val result = newsRepoImpl.getNewsByLatest(country)
            when (result) {
                is Resource.Success -> {
                    _homeState.value = HomeState.Latest(result.data)
                }

                is Resource.Error -> {
                    _homeState.value = HomeState.Error(result.throwable)
                }
            }
        }
    }

    fun getNewsByCategory(category: String,country: String) {
        viewModelScope.launch {
            _homeState.value = HomeState.Loading
            val result = newsRepoImpl.getNewsByCategory(category,country)
            when (result) {
                is Resource.Success -> {
                    _homeState.value = HomeState.Category(result.data)
                }

                is Resource.Error -> {
                    _homeState.value = HomeState.Error(result.throwable)
                }
            }
        }
    }

    fun getNewsByQuery(q: String,country: String) {
        viewModelScope.launch {
            _homeState.value = HomeState.Loading
            val result = newsRepoImpl.getNewsByQuery(q,country)
            when (result) {
                is Resource.Success -> {
                    _homeState.value = HomeState.Query(result.data)
                }

                is Resource.Error -> {
                    _homeState.value = HomeState.Error(result.throwable)
                }
            }
        }
    }


}