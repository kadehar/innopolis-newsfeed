package com.github.kadehar.newsfetcher.feature.mainscreen.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.kadehar.newsfetcher.R
import com.github.kadehar.newsfetcher.feature.mainscreen.domain.model.NewsDomainModel
import java.text.SimpleDateFormat

class NewsAdapter(private var news: List<NewsDomainModel>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

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
        private val image: ImageView = itemView.findViewById(R.id.ivArticlePhoto)
        private val title: TextView = itemView.findViewById(R.id.tvArticleTitle)
        private val description: TextView = itemView.findViewById(R.id.tvArticleDescription)
        private val publishedAt: TextView = itemView.findViewById(R.id.tvArticlePublishedAt)

        fun bind(article: NewsDomainModel) {
            val url = if (article.urlToImage != null) "${article.urlToImage}?w=360" else null
            Glide.with(itemView)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_non_existing_url)
                .fallback(R.drawable.ic_placeholder)
                .into(image)

            title.text = article.title
            description.text = article.description ?: ""

            val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")
            publishedAt.text = formatter.format(parser.parse(article.publishedAt) ?: "")
        }
    }

    fun updateArticles(newArticles: List<NewsDomainModel>) {
        news = newArticles
        notifyDataSetChanged()
    }
}