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
import com.selincengiz.news.databinding.ItemCategoryBinding
import com.selincengiz.news.databinding.ItemNewsBinding
import com.selincengiz.news.domain.entities.Category
import com.selincengiz.news.domain.entities.News
import okhttp3.internal.notify
import okhttp3.internal.notifyAll

class CategoryAdapter(private val itemListener: ItemCategoryListener) :
    ListAdapter<Category, CategoryAdapter.CategoryViewHolder>(CategoryDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), itemListener
        )

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) =
        holder.bind(getItem(position))

    class CategoryViewHolder(
        private val binding: ItemCategoryBinding,
        private val listener: ItemCategoryListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) = with(binding) {

            if (category.isSelected) {
                btnCategory.setBackground(root.resources.getDrawable(R.drawable.selected_category_background))
                btnCategory.setTextColor(root.resources.getColor(R.color.white))
            } else {
                btnCategory.setBackground(root.resources.getDrawable(R.drawable.category_background))
                btnCategory.setTextColor(root.resources.getColor(R.color.redBlack))
            }
            root.requestLayout()

            with(root.resources) {
                when (category.key) {
                    "business" -> btnCategory.text =
                        getString(R.string.business)

                    "crime" -> btnCategory.text =
                        getString(R.string.crime)

                    "education" -> btnCategory.text =
                        getString(R.string.education)

                    "entertainment" -> btnCategory.text =
                        getString(R.string.entertainment)

                    "health" -> btnCategory.text =
                        getString(R.string.health)

                    "lifestyle" -> btnCategory.text =
                        getString(R.string.lifestyle)

                    "politics" -> btnCategory.text =
                        getString(R.string.politics)

                    "science" -> btnCategory.text =
                        getString(R.string.science)

                    "sports" -> btnCategory.text =
                        getString(R.string.sports)

                    "technology" -> btnCategory.text =
                        getString(R.string.technology)

                    "tourism" -> btnCategory.text =
                        getString(R.string.tourism)

                    "world" -> btnCategory.text =
                        getString(R.string.world)

                    "other" -> btnCategory.text =
                        getString(R.string.other)

                }
            }




            btnCategory.setOnClickListener {

                listener.onCategoryClicked(category)
            }

        }

    }

    class CategoryDiffCallBack() : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }

    }

}

interface ItemCategoryListener {
    fun onCategoryClicked(category: Category)
}
