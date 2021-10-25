package com.github.kadehar.newsfetcher.feature.bookmarksscreen.di

import com.github.kadehar.newsfetcher.feature.bookmarksscreen.data.BookmarksRepository
import com.github.kadehar.newsfetcher.feature.bookmarksscreen.data.BookmarksRepoImpl
import com.github.kadehar.newsfetcher.feature.bookmarksscreen.data.local.BookmarkDAO
import com.github.kadehar.newsfetcher.feature.bookmarksscreen.domain.BookmarksInteractor
import com.github.kadehar.newsfetcher.feature.bookmarksscreen.ui.BookmarksScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val bookmarksModule = module {
    single<BookmarksRepository> {
        BookmarksRepoImpl(get<BookmarkDAO>())
    }

    single<BookmarksInteractor> {
        BookmarksInteractor(get<BookmarksRepository>())
    }

    viewModel<BookmarksScreenViewModel> {
        BookmarksScreenViewModel(get<BookmarksInteractor>())
    }
}