package com.github.kadehar.newsfetcher.di

import com.github.kadehar.newsfetcher.consts.HttpRoutes
import com.github.kadehar.newsfetcher.data.api.NewsApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single<OkHttpClient> {
        val httpLogging = HttpLoggingInterceptor()
        httpLogging.level = HttpLoggingInterceptor.Level.BODY

        OkHttpClient.Builder()
            .addInterceptor(httpLogging)
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(HttpRoutes.BASE_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<NewsApi> {
        get<Retrofit>().create(NewsApi::class.java)
    }
}