package com.selincengiz.news.presentation.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.selincengiz.news.R
import com.selincengiz.news.common.DetailState
import com.selincengiz.news.common.FavoriteState
import com.selincengiz.news.databinding.FragmentFavoriteBinding
import com.selincengiz.news.databinding.FragmentSplashBinding
import com.selincengiz.news.domain.entities.News
import com.selincengiz.news.presentation.home.ItemNewsListener
import com.selincengiz.news.presentation.home.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class FavoriteFragment : Fragment(), ItemNewsListener {

    private lateinit var binding: FragmentFavoriteBinding
    private val adapterNews by lazy { NewsAdapter(this) }
    private val viewModel by viewModels<FavoriteViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        binding.favoriteRecycler.adapter = adapterNews
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()
        viewModel.getFavorites()
    }

    fun observe() {
        lifecycleScope.launchWhenStarted {
            viewModel.favoriteState.collectLatest { state ->

                when (state) {
                    is FavoriteState.Entry -> {

                    }

                    is FavoriteState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is FavoriteState.Favorite -> {
                        binding.progressBar.visibility = View.GONE

                        adapterNews.submitList(state.list)
                    }

                    is FavoriteState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(
                            requireContext(),
                            state.throwable.message,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }

                }
            }
        }
    }

    override fun onClicked(news: News) {
        findNavController().navigate(FavoriteFragmentDirections.favoriteToDetail(news))
    }
}