package com.github.kadehar.newsfetcher.feature.bookmarksscreen.domain

import androidx.lifecycle.LiveData
import com.github.kadehar.newsfetcher.base.attempt
import com.github.kadehar.newsfetcher.feature.bookmarksscreen.data.BookmarksRepository
import com.github.kadehar.newsfetcher.feature.bookmarksscreen.data.local.BookmarkEntity
import com.github.kadehar.newsfetcher.feature.mainscreen.domain.model.NewsDomainModel

class BookmarksInteractor(private val repo: BookmarksRepository) {
    suspend fun create(article: NewsDomainModel) = repo.create(article)
    suspend fun read() = attempt { repo.read() }
    suspend fun update(article: NewsDomainModel) = repo.update(article)
    suspend fun delete(article: NewsDomainModel) = attempt {
        repo.delete(article)
    }
    suspend fun subscribeByDesc(): LiveData<List<NewsDomainModel>> = repo.subscribeByDesc()
}