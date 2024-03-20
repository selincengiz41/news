package com.selincengiz.news.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.selincengiz.news.R
import com.selincengiz.news.common.Extensions.loadUrl
import com.selincengiz.news.databinding.ItemNewsBinding
import com.selincengiz.news.domain.entities.News


class NewsAdapter(private val itemListener: ItemNewsListener) :
    ListAdapter<News, NewsAdapter.NewsViewHolder>(NewsDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), itemListener
        )

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) =
        holder.bind(getItem(position))

    class NewsViewHolder(
        private val binding: ItemNewsBinding,
        private val listener: ItemNewsListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(news: News) = with(binding) {

            if (news.imageUrl != null) {
                imageSlide.loadUrl(news.imageUrl)

            } else {
                imageSlide.setImageResource(R.drawable.default_news)
            }
            title.text = news.title
            author.text = news.sourceId
            date.text = news.pubDate



            root.setOnClickListener {
                listener.onClicked(news)
            }
        }

    }

    class NewsDiffCallBack() : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.articleId == newItem.articleId
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }

    }


}

interface ItemNewsListener {
    fun onClicked(news: News)
}
