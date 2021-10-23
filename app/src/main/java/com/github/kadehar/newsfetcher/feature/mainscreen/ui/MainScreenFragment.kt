package com.github.kadehar.newsfetcher.feature.mainscreen.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.kadehar.newsfetcher.base.openUrl
import com.github.kadehar.newsfetcher.databinding.FragmentMainscreenBinding
import com.github.kadehar.newsfetcher.feature.mainscreen.ui.adapter.NewsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainScreenFragment : Fragment() {
    private var _binding: FragmentMainscreenBinding? = null
    private val binding get() = _binding!!

    private val mainScreenViewModel: MainScreenViewModel by viewModel<MainScreenViewModel>()
    private val newsAdapter: NewsAdapter by lazy {
        NewsAdapter(
            news = listOf(),
            onBookmarkClick = mainScreenViewModel::onBookmarkClick,
            onItemClick = { article ->
                mainScreenViewModel.processUiEvent(UIEvent.OnArticleClick(article))
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainscreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvArticles.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
        }

        mainScreenViewModel.viewState.observe(viewLifecycleOwner, ::render)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun render(viewState: ViewState) {
        updateProgressBar(viewState)
        updateErrorText(viewState)
        updateList(viewState)
        openArticle(viewState)
    }

    private fun updateProgressBar(viewState: ViewState) {
        binding.newsProgressBar.isVisible = viewState.isLoading
    }

    private fun updateErrorText(viewState: ViewState) {
        binding.errorTextView.apply {
            text = viewState.errorMessage
            isVisible = viewState.isInErrorState
        }
    }

    private fun updateList(viewState: ViewState) {
        newsAdapter.updateArticles(viewState.articles)
    }

    private fun openArticle(viewState: ViewState) {
        viewState.article?.let {
            openUrl(requireActivity(), it.url)
        }
    }
}