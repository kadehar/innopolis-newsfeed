package com.github.kadehar.newsfetcher.feature.bookmarksscreen.ui

import com.github.kadehar.newsfetcher.base.Event
import com.github.kadehar.newsfetcher.feature.mainscreen.domain.model.NewsDomainModel

data class BookmarksViewState(
    val articles: List<NewsDomainModel>,
    val article: NewsDomainModel?
)

sealed class UIEvent : Event {
    object GetBookmarks : UIEvent()
    data class OnArticleClick(val article: NewsDomainModel) : UIEvent()
}

sealed class DataEvent : Event {
    data class RefreshDataBase(val articles: List<NewsDomainModel>) : DataEvent()
}