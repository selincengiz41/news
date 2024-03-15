package com.selincengiz.news.common

sealed interface RegisterState {
    object Entry : RegisterState
    object Loading : RegisterState
    data class Registered(val message: String) : RegisterState
    data class Error(val throwable: Throwable) : RegisterState
}