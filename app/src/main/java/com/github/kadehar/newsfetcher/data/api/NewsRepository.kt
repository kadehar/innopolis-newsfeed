package com.github.kadehar.newsfetcher.data.api

import com.github.kadehar.newsfetcher.feature.mainscreen.domain.model.NewsDomainModel

interface NewsRepository {
    suspend fun fetchNews(): List<NewsDomainModel>
}