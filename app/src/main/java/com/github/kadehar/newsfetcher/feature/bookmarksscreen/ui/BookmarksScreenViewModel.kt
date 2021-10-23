package com.github.kadehar.newsfetcher.feature.bookmarksscreen.ui

import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.github.kadehar.newsfetcher.base.BaseViewModel
import com.github.kadehar.newsfetcher.base.Event
import com.github.kadehar.newsfetcher.base.utils.SingleLiveEvent
import com.github.kadehar.newsfetcher.feature.bookmarksscreen.domain.BookmarksInteractor
import com.github.kadehar.newsfetcher.feature.mainscreen.domain.model.NewsDomainModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BookmarksScreenViewModel(private val interactor: BookmarksInteractor) :
    BaseViewModel<BookmarksViewState>() {

    val singleEvent = SingleLiveEvent<OpenArticleEvent.OnArticleClick>()

    init {
        viewModelScope.launch {
            interactor.subscribeByDesc().asFlow().collect {
                processUiEvent(UIEvent.OnBookmarksFetched(articles = it))
            }
        }
    }

    override fun initialViewState(): BookmarksViewState {
        return BookmarksViewState(
            articles = listOf(),
            article = null
        )
    }

    override suspend fun reduce(
        event: Event,
        previousState: BookmarksViewState
    ): BookmarksViewState? {
        when (event) {
            is UIEvent.OnBookmarksFetched -> {
                return previousState.copy(articles = event.articles)
            }
            is UIEvent.OnBookmarkClick -> {
                interactor.delete(event.article)
            }
            is DataEvent.RefreshDataBase -> {
                return previousState.copy(articles = event.articles)
            }
            is OpenArticleEvent.OnArticleClick -> {
                singleEvent.value = event
            }
        }
        return null
    }

    fun onBookmarkClick(article: NewsDomainModel) {
        processUiEvent(UIEvent.OnBookmarkClick(article))
    }
}