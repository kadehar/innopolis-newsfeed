package com.github.kadehar.newsfetcher.feature.mainscreen.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.kadehar.newsfetcher.R
import com.github.kadehar.newsfetcher.feature.mainscreen.ui.adapter.NewsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreenFragment : Fragment() {
    private val mainScreenViewModel: MainScreenViewModel by viewModel<MainScreenViewModel>()
    private val newsAdapter: NewsAdapter by lazy { NewsAdapter(listOf()) }
    private lateinit var progressBar: ProgressBar

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
        progressBar = view.findViewById(R.id.newsProgressBar)
        newsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        newsRecyclerView.adapter = newsAdapter
        mainScreenViewModel.viewState.observe(viewLifecycleOwner, ::render)
    }

    private fun render(viewState: ViewState) {
        updateProgressBar(viewState)
        updateList(viewState)
    }

    private fun updateProgressBar(viewState: ViewState) {
        progressBar.isVisible = viewState.isLoading
    }

    private fun updateList(viewState: ViewState) {
        newsAdapter.updateArticles(viewState.articles)
    }
}