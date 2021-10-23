package com.github.kadehar.newsfetcher.feature.bookmarksscreen.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.kadehar.newsfetcher.base.openUrl
import com.github.kadehar.newsfetcher.databinding.FragmentBookmarksScreenBinding
import com.github.kadehar.newsfetcher.feature.mainscreen.ui.adapter.NewsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookmarksScreenFragment : Fragment() {
    private var _binding: FragmentBookmarksScreenBinding? = null
    private val binding get() = _binding!!

    private val bookmarksScreenViewModel by viewModel<BookmarksScreenViewModel>()
    private val newsAdapter: NewsAdapter by lazy {
        NewsAdapter(
            news = listOf(),
            onBookmarkClick = {},
            onItemClick = { article ->
                bookmarksScreenViewModel.processUiEvent(UIEvent.OnArticleClick(article))
            }
        )
    }

    companion object {
        fun newInstance(): BookmarksScreenFragment {
            return BookmarksScreenFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookmarksScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvArticles.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
        }

        bookmarksScreenViewModel.viewState.observe(viewLifecycleOwner, ::render)
    }

    private fun render(viewState: BookmarksViewState) {
        newsAdapter.updateArticles(viewState.articles)
        openArticle(viewState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun openArticle(viewState: BookmarksViewState) {
        viewState.article?.let {
            openUrl(requireActivity(), it.url)
        }
    }
}