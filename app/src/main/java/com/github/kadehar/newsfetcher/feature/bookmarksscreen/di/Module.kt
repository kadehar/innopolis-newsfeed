package com.github.kadehar.newsfetcher.feature.bookmarksscreen.di

import com.github.kadehar.newsfetcher.feature.bookmarksscreen.data.BookmarksRepo
import com.github.kadehar.newsfetcher.feature.bookmarksscreen.data.BookmarksRepoImpl
import com.github.kadehar.newsfetcher.feature.bookmarksscreen.data.local.BookmarkDAO
import com.github.kadehar.newsfetcher.feature.bookmarksscreen.domain.BookmarksInteractor
import com.github.kadehar.newsfetcher.feature.bookmarksscreen.ui.BookmarksScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val bookmarksModule = module {
    single<BookmarksRepo> {
        BookmarksRepoImpl(get<BookmarkDAO>())
    }

    single<BookmarksInteractor> {
        BookmarksInteractor(get<BookmarksRepo>())
    }

    viewModel<BookmarksScreenViewModel> {
        BookmarksScreenViewModel(get<BookmarksInteractor>())
    }
}