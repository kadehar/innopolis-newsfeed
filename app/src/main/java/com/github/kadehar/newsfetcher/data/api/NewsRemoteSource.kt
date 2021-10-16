package com.github.kadehar.newsfetcher.data.api

import com.github.kadehar.newsfetcher.data.api.model.NewsModel

class NewsRemoteSource(private val api: NewsApi) {
    suspend fun fetchNews(): NewsModel = api.fetchNews()
}