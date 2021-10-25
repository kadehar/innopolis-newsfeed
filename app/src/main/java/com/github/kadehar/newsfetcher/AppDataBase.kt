package com.github.kadehar.newsfetcher

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.kadehar.newsfetcher.feature.bookmarksscreen.data.local.BookmarkDAO
import com.github.kadehar.newsfetcher.feature.bookmarksscreen.data.local.BookmarkEntity

@Database(entities = [BookmarkEntity::class], version = 11)
abstract class AppDataBase : RoomDatabase() {
    abstract fun bookmarksDAO(): BookmarkDAO
}