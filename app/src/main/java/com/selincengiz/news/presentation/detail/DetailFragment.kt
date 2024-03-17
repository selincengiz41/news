package com.selincengiz.news.presentation.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.selincengiz.news.R
import com.selincengiz.news.common.DetailState
import com.selincengiz.news.common.Extensions.loadUrl
import com.selincengiz.news.common.LoginState
import com.selincengiz.news.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private var favoriteState: Boolean = false
    private val args by navArgs<DetailFragmentArgs>()

    private val viewModel by viewModels<DetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            author.text = "Published by ${args.news.sourceId}"
            title.text = args.news.title
            date.text = args.news.pubDate

            author2.text = "Published by ${args.news.sourceId}"
            title2.text = args.news.title
            date2.text = args.news.pubDate

            if (args.news.imageUrl != null) {
                imageSlide.loadUrl(args.news.imageUrl)

            } else {
                imageSlide.setImageResource(R.drawable.default_news)
            }
            content.text = args.news.description

            backButton.setOnClickListener {
                findNavController().navigateUp()
            }


            requireActivity().findViewById<AppCompatButton>(R.id.floatingActionButton)
                .setOnClickListener {
                    if (favoriteState) {
                        viewModel.deleteFavorite(args.news)
                        it.setBackgroundDrawable(resources.getDrawable(R.drawable.fab))
                        favoriteState = false
                    } else {
                        viewModel.addFavorite(args.news)
                        it.setBackgroundDrawable(resources.getDrawable(R.drawable.selected_fab))
                        favoriteState = true

                    }
                }

            args.news.articleId?.let { viewModel.isFavorite(it) }

            observe()
        }

    }

    fun observe() {
        lifecycleScope.launchWhenStarted {
            viewModel.detailState.collectLatest { state ->

                when (state) {
                    is DetailState.Entry -> {

                    }

                    is DetailState.IsFavorite -> {

                        if (state.state) {
                            requireActivity().findViewById<AppCompatButton>(R.id.floatingActionButton)
                                .setBackgroundDrawable(resources.getDrawable(R.drawable.selected_fab))
                            favoriteState = true
                        } else {
                            requireActivity().findViewById<AppCompatButton>(R.id.floatingActionButton)
                                .setBackgroundDrawable(resources.getDrawable(R.drawable.fab))
                            favoriteState = false

                        }
                    }

                    is DetailState.Error -> {
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
}