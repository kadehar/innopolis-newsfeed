package com.github.kadehar.newsfetcher.feature.bookmarksscreen.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.kadehar.newsfetcher.databinding.FragmentBookmarksScreenBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookmarksScreenFragment : Fragment() {
    private var _binding: FragmentBookmarksScreenBinding? = null
    private val binding get() = _binding!!

    private val bookmarksScreenViewModel by viewModel<BookmarksScreenViewModel>()


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

        bookmarksScreenViewModel.viewState.observe(viewLifecycleOwner, ::render)
    }

    private fun render(viewState: BookmarksViewState) {
        binding.bkmTitle.text = viewState.articles.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}