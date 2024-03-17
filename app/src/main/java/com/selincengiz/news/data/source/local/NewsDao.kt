package com.selincengiz.news.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.selincengiz.news.data.entities.NewsRoom

@Dao
interface NewsDao {

    @Query("SELECT * FROM news_favorite ")
    suspend fun getFavorites(): List<NewsRoom>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorites(news: NewsRoom)

    @Delete
    suspend fun deleteFavorites(news: NewsRoom)

    @Query("DELETE  FROM news_favorite")
    suspend fun nukeTable()
}