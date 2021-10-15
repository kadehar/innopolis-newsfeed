package com.github.kadehar.newsfetcher.feature.mainscreen.di

import com.github.kadehar.newsfetcher.data.api.NewsRemoteSource
import com.github.kadehar.newsfetcher.data.api.NewsRepository
import com.github.kadehar.newsfetcher.data.api.NewsRepositoryImpl
import com.github.kadehar.newsfetcher.feature.mainscreen.MainScreenViewModel
import com.github.kadehar.newsfetcher.feature.mainscreen.domain.MainScreenNewsInteractor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainScreenModule = module {
    viewModel<MainScreenViewModel>{
        MainScreenViewModel()
    }

    single<NewsRepository> {
        NewsRepositoryImpl(get<NewsRemoteSource>())
    }

    single<MainScreenNewsInteractor> {
        MainScreenNewsInteractor(get<NewsRepository>())
    }
}