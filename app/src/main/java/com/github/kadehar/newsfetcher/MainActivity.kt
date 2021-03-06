package com.github.kadehar.newsfetcher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.kadehar.newsfetcher.feature.mainscreen.ui.MainScreenFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainScreenFragment())
            .commit()
    }
}