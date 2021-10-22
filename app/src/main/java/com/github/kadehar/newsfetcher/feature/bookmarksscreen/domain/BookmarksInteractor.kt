package com.github.kadehar.newsfetcher.feature.bookmarksscreen.domain

import com.github.kadehar.newsfetcher.base.attempt
import com.github.kadehar.newsfetcher.feature.bookmarksscreen.data.BookmarksRepo
import com.github.kadehar.newsfetcher.feature.mainscreen.domain.model.NewsDomainModel

class BookmarksInteractor(private val repo: BookmarksRepo) {
    suspend fun create(article: NewsDomainModel) = repo.create(article)
    suspend fun read() = attempt { repo.read() }
    suspend fun update(article: NewsDomainModel) = repo.update(article)
    suspend fun delete(article: NewsDomainModel) = repo.delete(article)
}