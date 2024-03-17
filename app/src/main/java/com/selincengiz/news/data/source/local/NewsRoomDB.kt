package com.selincengiz.news.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.selincengiz.news.data.entities.NewsRoom

@Database(entities = [NewsRoom::class], version = 1)
abstract class NewsRoomDB :RoomDatabase() {

    abstract fun newsDao():NewsDao
}