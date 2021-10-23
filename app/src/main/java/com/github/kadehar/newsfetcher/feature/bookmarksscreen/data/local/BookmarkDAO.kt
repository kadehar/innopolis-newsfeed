package com.github.kadehar.newsfetcher.feature.bookmarksscreen.data.local

import androidx.room.*

@Dao
interface BookmarkDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(entity: BookmarkEntity)

    @Query("SELECT * FROM ${BookmarkEntity.TABLE_NAME} ORDER BY id DESC")
    suspend fun read(): List<BookmarkEntity>

    @Update
    suspend fun update(entity: BookmarkEntity)

    @Delete
    suspend fun delete(entity: BookmarkEntity)
}