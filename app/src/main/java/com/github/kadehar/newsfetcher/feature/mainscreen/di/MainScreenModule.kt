package com.github.kadehar.newsfetcher.feature.mainscreen.di

import com.github.kadehar.newsfetcher.feature.mainscreen.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainScreenModule = module {
    viewModel<MainScreenViewModel>{
        MainScreenViewModel()
    }
}