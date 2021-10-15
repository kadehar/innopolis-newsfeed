package com.github.kadehar.newsfetcher.data.api

class NewsRepositoryImpl(private val source: NewsRemoteSource) : NewsRepository {
    override suspend fun fetchNews(topic: String): String {
        return source.fetchNews(topic).articles.first().content //TODO: Change to mapper function
    }
}