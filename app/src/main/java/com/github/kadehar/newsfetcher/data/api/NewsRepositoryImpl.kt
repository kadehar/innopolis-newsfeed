package com.github.kadehar.newsfetcher.data.api

import com.github.kadehar.newsfetcher.data.toDomain
import com.github.kadehar.newsfetcher.feature.mainscreen.domain.model.NewsDomainModel

class NewsRepositoryImpl(private val source: NewsRemoteSource) : NewsRepository {
    override suspend fun fetchNews(): List<NewsDomainModel> {
        return source.fetchNews().articles.map { article -> article.toDomain() }
    }
}