package com.github.kadehar.newsfetcher.feature.mainscreen.domain

import com.github.kadehar.newsfetcher.data.api.NewsRepository

class MainScreenNewsInteractor(private val repository: NewsRepository) {
    suspend fun fetchNews(topic: String): String {
        return repository.fetchNews(topic)
    }
}