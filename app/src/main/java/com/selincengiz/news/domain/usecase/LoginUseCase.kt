package com.selincengiz.news.domain.usecase

import com.selincengiz.news.common.Resource
import com.selincengiz.news.data.repo.AuthRepoImpl
import com.selincengiz.news.domain.repo.AuthRepo

class LoginUseCase(private val authRepoImpl: AuthRepo) {

    suspend operator fun invoke(
        email: String?,
        password: String?
    ): Resource<String> {
        if (email.isNullOrEmpty().not() && password.isNullOrEmpty().not()) {
            if (password!!.length >= 6) {
                return authRepoImpl.firebaseLogin(email!!, password!!)

            } else {
                return Resource.Error(Exception("Password must be at least 6 characters."))
            }

        } else {
            return Resource.Error(Exception("Please fill in the blanks."))

        }

    }
}