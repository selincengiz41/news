package com.selincengiz.news.di


import com.selincengiz.news.data.repo.AuthRepoImpl
import com.selincengiz.news.domain.repo.AuthRepo
import com.selincengiz.news.domain.usecase.LoginUseCase
import com.selincengiz.news.domain.usecase.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule  {

    @Provides
    @Singleton
    fun provideLoginUseCase(authRepoImpl: AuthRepo) =
        LoginUseCase(authRepoImpl = authRepoImpl)

    @Provides
    @Singleton
    fun provideRegisterUseCase(authRepoImpl: AuthRepo) =
        RegisterUseCase(authRepoImpl = authRepoImpl)

}