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
import com.selincengiz.news.databinding.ItemSliderBinding
import com.selincengiz.news.domain.entities.News
import okhttp3.internal.wait


class SliderAdapter(private val itemListener: ItemSliderListener) :
    ListAdapter<News, SliderAdapter.SliderViewHolder>(CartDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder =
        SliderViewHolder(
            ItemSliderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), itemListener
        )

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) =
        holder.bind(getItem(position))

    class SliderViewHolder(
        private val binding: ItemSliderBinding,
        private val listener: ItemSliderListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(news: News) = with(binding) {

            if (news.imageUrl != null) {
                imageSlide.loadUrl(news.imageUrl)

            } else {
                imageSlide.setImageResource(R.drawable.default_news)
            }

            title.text=news.title
            author.text="by ${news.sourceId}"

            root.setOnClickListener {
                listener.onClicked(news)
            }
        }

    }

    class CartDiffCallBack() : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.articleId == newItem.articleId
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }

    }


}

interface ItemSliderListener {
    fun onClicked(news: News)

}
