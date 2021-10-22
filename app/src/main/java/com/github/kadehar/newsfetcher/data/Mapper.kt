package com.github.kadehar.newsfetcher.data

import com.github.kadehar.newsfetcher.data.api.model.NewsArticleModel
import com.github.kadehar.newsfetcher.data.api.model.NewsSourceModel
import com.github.kadehar.newsfetcher.feature.mainscreen.domain.model.NewsDomainModel
import com.github.kadehar.newsfetcher.feature.mainscreen.domain.model.NewsSourceDomainModel

fun NewsArticleModel.toDomain(): NewsDomainModel =
    NewsDomainModel(
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt
    )