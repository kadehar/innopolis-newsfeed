package com.github.kadehar.newsfetcher.feature.mainscreen.ui

import com.github.kadehar.newsfetcher.base.BaseViewModel
import com.github.kadehar.newsfetcher.base.Event
import com.github.kadehar.newsfetcher.feature.mainscreen.domain.MainScreenNewsInteractor

class MainScreenViewModel(private val newsInteractor: MainScreenNewsInteractor) :
    BaseViewModel<ViewState>() {

    init {
        processUiEvent(UIEvent.GetCurrentNews)
    }

    override fun initialViewState(): ViewState {
        return ViewState(
            articles = listOf(),
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
            is UIEvent.OnArticleClick -> {
                event.article
                // TODO: Nav
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

}