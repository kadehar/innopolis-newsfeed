package com.github.kadehar.newsfetcher.data

import com.github.kadehar.newsfetcher.data.api.model.NewsArticleModel
import com.github.kadehar.newsfetcher.data.api.model.NewsSourceModel
import com.github.kadehar.newsfetcher.feature.mainscreen.domain.model.NewsDomainModel
import com.github.kadehar.newsfetcher.feature.mainscreen.domain.model.NewsSourceDomainModel

fun NewsSourceModel.toDomain(): NewsSourceDomainModel =
    NewsSourceDomainModel(
        id = id,
        name = name
    )

fun NewsArticleModel.toDomain(): NewsDomainModel =
    NewsDomainModel(
        source = source.toDomain(),
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content
    )