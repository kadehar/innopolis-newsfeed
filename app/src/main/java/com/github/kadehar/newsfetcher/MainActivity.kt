package com.github.kadehar.newsfetcher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.kadehar.newsfetcher.databinding.ActivityMainBinding
import com.github.kadehar.newsfetcher.feature.bookmarksscreen.ui.BookmarksScreenFragment
import com.github.kadehar.newsfetcher.feature.mainscreen.ui.MainScreenFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bnvMenu.apply {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.bnmNewsTab -> {
                        navigation(MainScreenFragment())
                    }
                    R.id.bnmBookmarksTab -> {
                        navigation(BookmarksScreenFragment.newInstance())
                    }
                }
                true
            }
            selectedItemId = R.id.bnmNewsTab
        }
    }

    private fun navigation(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}