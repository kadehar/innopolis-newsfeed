package com.github.kadehar.newsfetcher.feature.bookmarksscreen.data

import com.github.kadehar.newsfetcher.feature.mainscreen.domain.model.NewsDomainModel

interface BookmarksRepo {
    suspend fun create(article: NewsDomainModel)
    suspend fun read(): List<NewsDomainModel>
    suspend fun update(article: NewsDomainModel)
    suspend fun delete(article: NewsDomainModel)
}