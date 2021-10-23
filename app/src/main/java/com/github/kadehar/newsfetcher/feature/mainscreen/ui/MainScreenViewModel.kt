package com.github.kadehar.newsfetcher.feature.mainscreen.ui

import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.github.kadehar.newsfetcher.base.BaseViewModel
import com.github.kadehar.newsfetcher.base.Event
import com.github.kadehar.newsfetcher.feature.bookmarksscreen.domain.BookmarksInteractor
import com.github.kadehar.newsfetcher.feature.mainscreen.domain.MainScreenNewsInteractor
import com.github.kadehar.newsfetcher.feature.mainscreen.domain.model.NewsDomainModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val newsInteractor: MainScreenNewsInteractor,
    private val bookmarksInteractor: BookmarksInteractor
) :
    BaseViewModel<ViewState>() {

    init {
        viewModelScope.launch {
            bookmarksInteractor.subscribeByDesc().asFlow().collect {
                processUiEvent(UIEvent.OnBookmarksFetched(articles = it))
            }
        }
        processUiEvent(UIEvent.GetCurrentNews)
    }

    override fun initialViewState(): ViewState {
        return ViewState(
            articles = listOf(),
            article = null,
            errorMessage = null,
            isLoading = false
        )
    }

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is UIEvent.GetCurrentNews -> {
                processDataEvent(DataEvent.OnDataLoad)
                newsInteractor.fetchNews().fold(
                    onError = {
                        processDataEvent(DataEvent.ErrorNewsRequest(it.localizedMessage ?: ""))
                    },
                    onSuccess = {
                        processDataEvent(DataEvent.SuccessNewsRequest(it))
                    }
                )
            }
            is UIEvent.OnBookmarkClick -> {
                bookmarksInteractor.create(event.article.copy(isBookmarked = true))
            }
            is UIEvent.OnArticleClick -> {
                return previousState.copy(article = event.article)
            }
            is UIEvent.OnBookmarksFetched -> {
                val oldArticles = previousState.articles
                val newArticles = event.articles

                val articles = oldArticles.map { article ->
                    article.copy(isBookmarked = newArticles.map { it.url }
                        .contains(article.url))
                }

                return previousState.copy(articles = articles)
            }
            is DataEvent.OnDataLoad -> {
                return previousState.copy(isLoading = true)
            }
            is DataEvent.SuccessNewsRequest -> {
                return previousState.copy(
                    articles = event.articles,
                    errorMessage = null,
                    isLoading = false
                )
            }
            is DataEvent.ErrorNewsRequest -> {
                return previousState.copy(
                    isLoading = false,
                    errorMessage = event.errorMessage
                )
            }
        }
        return null
    }

    fun onBookmarkClick(article: NewsDomainModel) {
        processUiEvent(UIEvent.OnBookmarkClick(article))
    }

}