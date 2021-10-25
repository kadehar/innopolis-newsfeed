package com.github.kadehar.newsfetcher

import android.app.Application
import com.github.kadehar.newsfetcher.di.appModule
import com.github.kadehar.newsfetcher.di.databaseModule
import com.github.kadehar.newsfetcher.feature.bookmarksscreen.di.bookmarksModule
import com.github.kadehar.newsfetcher.feature.mainscreen.di.mainScreenModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NewsFeedApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@NewsFeedApp)
            modules(appModule, mainScreenModule, databaseModule, bookmarksModule)
        }
    }
}