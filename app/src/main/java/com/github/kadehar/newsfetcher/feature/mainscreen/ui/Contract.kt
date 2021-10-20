package com.github.kadehar.newsfetcher.feature.mainscreen.ui

import com.github.kadehar.newsfetcher.base.Event
import com.github.kadehar.newsfetcher.feature.mainscreen.domain.model.NewsDomainModel

data class ViewState(
    val articles: List<NewsDomainModel>,
    val errorMessage: String?,
    val isLoading: Boolean
)

sealed class UIEvent : Event {
    object GetCurrentNews : UIEvent()
    object OnArticleClick : UIEvent()
}

sealed class DataEvent : Event {
    object OnDataLoad : DataEvent()
    data class SuccessNewsRequest(
        val articles: List<NewsDomainModel>
    ) : DataEvent()
    data class ErrorNewsRequest(val errorMessage: String) : DataEvent()
}