package com.github.kadehar.newsfetcher.feature.bookmarksscreen.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.kadehar.newsfetcher.feature.bookmarksscreen.data.local.BookmarkEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class BookmarkEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "image") val imageUrl: String,
    @ColumnInfo(name = "author") val author: String?,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "publishedAt") val publishedAt: String
) {
    companion object {
        const val TABLE_NAME = "BOOKMARKS_TABLE"
    }
}