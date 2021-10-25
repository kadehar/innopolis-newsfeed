package com.github.kadehar.newsfetcher.feature.mainscreen.domain

import com.github.kadehar.newsfetcher.base.attempt
import com.github.kadehar.newsfetcher.base.mapToList
import com.github.kadehar.newsfetcher.data.api.NewsRepository
import com.github.kadehar.newsfetcher.feature.bookmarksscreen.data.BookmarksRepository

class MainScreenNewsInteractor(
    private val newsRepository: NewsRepository,
    private val bookmarksRepository: BookmarksRepository
) {
    suspend fun fetchNews() = attempt {
        val news = newsRepository.fetchNews()
        val bookmarks = bookmarksRepository.read()
        mapToList(oldList = news, newList = bookmarks)
    }
}