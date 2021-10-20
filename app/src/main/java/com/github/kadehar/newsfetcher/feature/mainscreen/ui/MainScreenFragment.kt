package com.github.kadehar.newsfetcher.feature.mainscreen.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.kadehar.newsfetcher.R
import com.github.kadehar.newsfetcher.feature.mainscreen.ui.adapter.NewsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreenFragment : Fragment() {
    private val mainScreenViewModel: MainScreenViewModel by viewModel<MainScreenViewModel>()
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mainscreen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newsRecyclerView: RecyclerView = view.findViewById(R.id.rv_news)
        newsAdapter = NewsAdapter()
        newsRecyclerView.adapter = newsAdapter
        mainScreenViewModel.viewState.observe(viewLifecycleOwner, ::render)
    }

    private fun render(viewState: ViewState) {
        newsAdapter.news = viewState.articles
        newsAdapter.notifyDataSetChanged()
    }
}