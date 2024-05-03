package com.selincengiz.news.presentation.notification

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.selincengiz.news.R
import com.selincengiz.news.common.Extensions.loadUrl
import com.selincengiz.news.databinding.ItemNewsBinding
import com.selincengiz.news.databinding.ItemNotificationBinding
import com.selincengiz.news.domain.entities.News
import com.selincengiz.news.domain.entities.Notification
import com.selincengiz.news.presentation.home.NewsAdapter

class NotificationAdapter() :
    ListAdapter<Notification, NotificationAdapter.NotificationViewHolder>(NotificationDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder =
        NotificationViewHolder(
            ItemNotificationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) =
        holder.bind(getItem(position))

    class NotificationViewHolder(
        private val binding: ItemNotificationBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(notification: Notification) = with(binding) {

            title.text = notification.title
            desc.text = notification.desc

        }

    }

    class NotificationDiffCallBack() : DiffUtil.ItemCallback<Notification>() {
        override fun areItemsTheSame(oldItem: Notification, newItem: Notification): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Notification, newItem: Notification): Boolean {
            return oldItem == newItem
        }

    }


}

