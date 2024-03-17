package com.selincengiz.news.di

import android.content.Context
import androidx.room.Room
import com.selincengiz.news.data.source.local.NewsRoomDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDBModule {

    @Provides
    @Singleton
    fun provideRoomDB(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, NewsRoomDB::class.java, "news_favorite_db").build()

    @Provides
    @Singleton
    fun provideRoomDao(roomDB: NewsRoomDB) = roomDB.newsDao()
}