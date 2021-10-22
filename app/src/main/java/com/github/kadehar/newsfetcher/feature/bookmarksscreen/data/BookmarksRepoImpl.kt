package com.github.kadehar.newsfetcher.feature.bookmarksscreen.data

import com.github.kadehar.newsfetcher.feature.bookmarksscreen.data.local.BookmarkDAO
import com.github.kadehar.newsfetcher.feature.mainscreen.domain.model.NewsDomainModel

class BookmarksRepoImpl(private val dao: BookmarkDAO) : BookmarksRepo {
    override suspend fun create(article: NewsDomainModel) {
        dao.create(article.toEntity())
    }

    override suspend fun read(): List<NewsDomainModel> {
        return dao.read().map { it.toDomain() }
    }

    override suspend fun update(article: NewsDomainModel) {
        dao.update(article.toEntity())
    }

    override suspend fun delete(article: NewsDomainModel) {
        dao.delete(article.toEntity())
    }
}