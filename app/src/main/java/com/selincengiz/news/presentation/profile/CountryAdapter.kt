package com.selincengiz.news.presentation.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.selincengiz.news.R
import com.selincengiz.news.databinding.ItemCategoryBinding
import com.selincengiz.news.databinding.ItemCountryBinding
import com.selincengiz.news.domain.entities.Category
import com.selincengiz.news.domain.entities.Countries
import com.selincengiz.news.presentation.home.CategoryAdapter

class CountryAdapter(private val itemListener: ItemCountryListener) :
    ListAdapter<Countries, CountryAdapter.CountryViewHolder>(CountryDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder =
        CountryViewHolder(
            ItemCountryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), itemListener
        )

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) =
        holder.bind(getItem(position))

    class CountryViewHolder(
        private val binding: ItemCountryBinding,
        private val listener: ItemCountryListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(country: Countries) = with(binding) {


            if (country.isSelected) {
                image.setImageDrawable(country.drawable)
                image.strokeWidth=5f
            } else {
                image.setImageDrawable(country.drawable)
                image.strokeWidth=0f

            }



            image.setOnClickListener {

                listener.onCountryClicked(country)
            }

        }

    }

    class CountryDiffCallBack() : DiffUtil.ItemCallback<Countries>() {
        override fun areItemsTheSame(oldItem: Countries, newItem: Countries): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Countries, newItem: Countries): Boolean {
            return oldItem == newItem
        }

    }

}

interface ItemCountryListener {
    fun onCountryClicked(country: Countries)
}
