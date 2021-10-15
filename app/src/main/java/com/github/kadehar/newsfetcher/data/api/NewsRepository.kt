package com.github.kadehar.newsfetcher.data.api

interface NewsRepository {
    suspend fun fetchNews(topic: String): String //TODO: Change to domain model
}