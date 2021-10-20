package com.github.kadehar.newsfetcher.feature.mainscreen.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.kadehar.newsfetcher.R
import com.github.kadehar.newsfetcher.feature.mainscreen.domain.model.NewsDomainModel

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    var news: List<NewsDomainModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val citiesView = inflater.inflate(R.layout.articles_list_item, parent, false)
        return ViewHolder(citiesView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = news[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int = news.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val articleTitle: TextView = itemView.findViewById(R.id.article_title)
        private val articleContent: TextView = itemView.findViewById(R.id.article_content)

        fun bind(article: NewsDomainModel) {
            articleTitle.text = article.title
            articleContent.text = article.content
        }
    }
}