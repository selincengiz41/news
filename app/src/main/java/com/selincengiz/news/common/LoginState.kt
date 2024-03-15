package com.selincengiz.news.common


sealed interface LoginState {
    object Entry : LoginState
    object Loading : LoginState
    data class Logined(val message: String) : LoginState
    data class Error(val throwable: Throwable) : LoginState

}