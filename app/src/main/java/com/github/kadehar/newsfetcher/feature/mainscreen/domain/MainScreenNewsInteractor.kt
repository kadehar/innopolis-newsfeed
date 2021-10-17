package com.github.kadehar.newsfetcher.feature.mainscreen.domain

import com.github.kadehar.newsfetcher.data.api.NewsRepository
import com.github.kadehar.newsfetcher.feature.mainscreen.domain.model.NewsDomainModel

class MainScreenNewsInteractor(private val repository: NewsRepository) {
    suspend fun fetchNews(): List<NewsDomainModel> {
        return repository.fetchNews()
    }
}