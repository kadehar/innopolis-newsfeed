package com.github.kadehar.newsfetcher.data.api

import com.github.kadehar.newsfetcher.consts.HttpRoutes
import com.github.kadehar.newsfetcher.data.api.model.NewsModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsApi {
    @GET(HttpRoutes.NEWS_PATH)
    @Headers("X-Api-Key: 59b3296766e7493e88a8a962a688e765")
    suspend fun fetchNews(
        @Query("country") language: String = "ru"
    ) : NewsModel
}