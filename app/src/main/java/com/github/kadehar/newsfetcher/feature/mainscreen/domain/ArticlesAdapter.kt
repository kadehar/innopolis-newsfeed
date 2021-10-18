package com.github.kadehar.newsfetcher.feature.mainscreen.domain

import androidx.recyclerview.widget.DiffUtil
import com.github.kadehar.newsfetcher.data.api.model.NewsArticleModel
import com.github.kadehar.newsfetcher.feature.mainscreen.domain.model.NewsDomainModel
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

class ArticlesAdapter : AsyncListDifferDelegationAdapter<NewsDomainModel>(ArticleDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(articleAdapterDelegate())
    }

    private fun articleAdapterDelegate() =
        adapterDelegateViewBinding<NewsDomainModel, NewsDomainModel, ItemArticleBinding>(
            { layoutInflater, parent -> ItemArticleBinding.inflate(layoutInflater, parent, false) }
        ) {

            bind {
                binding.apply {
//                    authorTextView.text = item.author
//                    contentTextView.text = item.content
                }
            }
        }

    class ArticleDiffUtilCallback : DiffUtil.ItemCallback<NewsDomainModel>() {
        override fun areItemsTheSame(oldItem: NewsDomainModel, newItem: NewsDomainModel): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: NewsDomainModel, newItem: NewsDomainModel): Boolean {
            return oldItem == newItem
        }
    }
}