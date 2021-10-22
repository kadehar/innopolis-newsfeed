package com.github.kadehar.newsfetcher.feature.mainscreen.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.kadehar.newsfetcher.R
import com.github.kadehar.newsfetcher.databinding.ArticlesListItemBinding
import com.github.kadehar.newsfetcher.feature.mainscreen.domain.model.NewsDomainModel
import java.text.SimpleDateFormat

class NewsAdapter(
    private var news: List<NewsDomainModel>,
    private val onItemClick: (article: NewsDomainModel) -> Unit
) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val articlesBinding =
            ArticlesListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )

        return ViewHolder(articlesBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = news[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int = news.size

    inner class ViewHolder(private val articlesBinding: ArticlesListItemBinding) :
        RecyclerView.ViewHolder(articlesBinding.root) {
        fun bind(article: NewsDomainModel) {
            articlesBinding.apply {
                val url = if (article.urlToImage != null) "${article.urlToImage}?w=360" else null
                Glide.with(itemView)
                    .load(url)
                    .centerCrop()
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_non_existing_url)
                    .fallback(R.drawable.ic_placeholder)
                    .into(ivArticlePhoto)

                tvArticleTitle.text = article.title
                tvArticleDescription.text = article.description ?: ""

                val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")
                tvArticlePublishedAt.text =
                    formatter.format(parser.parse(article.publishedAt) ?: "")
            }

            itemView.setOnClickListener {
                onItemClick(article)
            }
        }
    }

    fun updateArticles(newArticles: List<NewsDomainModel>) {
        news = newArticles
        notifyDataSetChanged()
    }
}