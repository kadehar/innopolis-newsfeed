package com.github.kadehar.newsfetcher.feature.bookmarksscreen.ui

import android.util.Log
import com.github.kadehar.newsfetcher.base.BaseViewModel
import com.github.kadehar.newsfetcher.base.Event
import com.github.kadehar.newsfetcher.feature.bookmarksscreen.domain.BookmarksInteractor

class BookmarksScreenViewModel(private val interactor: BookmarksInteractor) :
    BaseViewModel<BookmarksViewState>() {

    init {
        processUiEvent(UIEvent.GetBookmarks)
    }

    override fun initialViewState(): BookmarksViewState {
        return BookmarksViewState(listOf())
    }

    override suspend fun reduce(event: Event, previousState: BookmarksViewState): BookmarksViewState? {
        when (event) {
            is UIEvent.GetBookmarks -> {
                interactor.read().fold(
                    onError = {
                        Log.d("DB_ERRORS", it.localizedMessage ?: "No Data")
                    },
                    onSuccess = {
                        processDataEvent(DataEvent.RefreshDataBase(it))
                    }
                )
            }
            is DataEvent.RefreshDataBase -> {
                return previousState.copy(articles = event.articles)
            }
        }
        return null
    }
}