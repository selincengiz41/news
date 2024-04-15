package com.selincengiz.news.domain.repo

import com.selincengiz.news.common.Resource

interface AuthRepo {

    suspend fun firebaseLogin(email: String, password: String): Resource<String>

    suspend fun firebaseRegister(email: String, password: String, name: String): Resource<String>
}