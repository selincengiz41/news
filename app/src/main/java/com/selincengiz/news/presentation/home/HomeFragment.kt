package com.selincengiz.news.presentation.home

import android.content.ContentValues.TAG
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.selincengiz.news.R
import com.selincengiz.news.common.Country
import com.selincengiz.news.common.HomeState
import com.selincengiz.news.common.Keyboard.isKeyboardShowing
import com.selincengiz.news.databinding.FragmentHomeBinding
import com.selincengiz.news.domain.entities.Category
import com.selincengiz.news.domain.entities.News
import com.tistory.zladnrms.roundablelayout.RoundableLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import java.util.Timer
import java.util.TimerTask


@AndroidEntryPoint
class HomeFragment : Fragment(), ItemSliderListener, ItemNewsListener, ItemCategoryListener,
    SearchView.OnQueryTextListener, OnGlobalLayoutListener {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()
    private val adapter by lazy { SliderAdapter(this) }
    private val adapterNews by lazy { NewsAdapter(this) }
    private val adapterCategory by lazy { CategoryAdapter(this) }
    private val slideHandler = Handler()
    private var categoryList = mutableListOf<Category>(
        Category("business", true),
        Category("crime", false),
        Category("education", false),
        Category("entertainment", false),
        Category("health", false),
        Category("lifestyle", false),
        Category("politics", false),
        Category("science", false),
        Category("sports", false),
        Category("technology", false),
        Category("tourism", false),
        Category("world", false),
        Category("other", false)


    )
    var currentPage = 0
    var timer: Timer? = null
    val DELAY_MS: Long = 500 //delay in milliseconds before task is to be executed
    val PERIOD_MS: Long = 5000 // time in milliseconds between successive task executions.


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        viewPager()
        binding.newsRecycler.adapter = adapterNews
        binding.categoryRecycler.adapter = adapterCategory
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getNewsByLatest(Country.COUNTRY)
        viewModel.getNewsByCategory("business", Country.COUNTRY)
        binding.searchView.setOnQueryTextListener(this)
        binding.root.getViewTreeObserver().addOnGlobalLayoutListener(this)

        observe()


    }

    fun onKeyboardVisibilityChanged(opened: Boolean) {
        if (!opened) {
            binding.mainContainer
                .transitionToStart()
            requireActivity().findViewById<RoundableLayout>(R.id.container_bottomNavigationView)
                ?.let {
                    it.visibility =
                        View.VISIBLE
                }


        } else {
            binding.mainContainer
                .transitionToEnd()

            requireActivity().findViewById<RoundableLayout>(R.id.container_bottomNavigationView)
                ?.let {
                    it.visibility =
                        View.GONE
                }

        }
    }


    fun observe() {
        lifecycleScope.launchWhenStarted {
            viewModel.homeState.collectLatest { state ->

                when (state) {
                    HomeState.Loading -> {

                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is HomeState.Latest -> {

                        if (state.list.size < 15) {
                            adapter.submitList(state.list)
                        } else {
                            adapter.submitList(state.list.subList(0, 15))
                        }
                        adapterCategory.submitList(categoryList)
                        binding.progressBar.visibility = View.GONE
                    }

                    is HomeState.Category -> {

                        adapterNews.submitList(state.list)


                        binding.progressBar.visibility = View.GONE
                    }

                    is HomeState.Query -> {

                        adapterNews.submitList(state.list)

                        binding.progressBar.visibility = View.GONE
                    }


                    is HomeState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(
                            requireContext(),
                            state.throwable.message,
                            Toast.LENGTH_SHORT
                        )
                            .show()


                    }

                    else -> {

                    }
                }

            }
        }

    }

    fun viewPager() = with(binding) {
        viewPager.adapter = adapter
        viewPager.clipToPadding = false
        viewPager.clipChildren = false
        viewPager.offscreenPageLimit = 3
        viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER


        val pageTransformer = CompositePageTransformer()
        pageTransformer.addTransformer(MarginPageTransformer(40))
        pageTransformer.addTransformer(object : ViewPager2.PageTransformer {
            override fun transformPage(page: View, position: Float) {
                val r = 1 - Math.abs(position)
                page.scaleY = 0.85f + r * 0.15f
            }

        })

        viewPager.setPageTransformer(pageTransformer)
        viewPager.currentItem = 1
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                slideHandler.removeCallbacks(slideRunnable)

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)

                currentPage = position
            }


        })

        /*After setting the adapter use the timer */


        timer = Timer() // This will create a new Thread

        timer!!.schedule(object : TimerTask() {
            // task to be scheduled
            override fun run() {
                handler.post(Update)
            }
        }, DELAY_MS, PERIOD_MS)


    }

    val handler = Handler()
    val Update = Runnable {
        if (currentPage != adapter.itemCount - 1) {
            currentPage++
        } else {
            currentPage = 0
        }
        binding.viewPager.setCurrentItem(currentPage, true)
    }
    val slideRunnable = Runnable {
        kotlin.run {
            binding.viewPager.currentItem = binding.viewPager.currentItem
        }

    }

    override fun onPause() {
        super.onPause()
        slideHandler.removeCallbacks(slideRunnable)
        handler.removeCallbacks(Update)
    }

    override fun onResume() {
        super.onResume()
        slideHandler.postDelayed(slideRunnable, 2000)
        handler.postDelayed(Update, 2000)
    }

    override fun onClicked(news: News) {
        findNavController().navigate(HomeFragmentDirections.homeToDetail(news))
    }

    override fun onCategoryClicked(category: Category) {
        categoryList.forEach {
            it.isSelected = it.key == category.key

        }
        adapterCategory.notifyDataSetChanged()
        viewModel.getNewsByCategory(category.key, Country.COUNTRY)

    }

    override fun onQueryTextSubmit(text: String?): Boolean {
        text?.let {
            if (it.length > 3) {
                viewModel.getNewsByQuery(it, Country.COUNTRY)
            }
        }
        return true
    }

    override fun onQueryTextChange(text: String?): Boolean {
        text?.let {
            if (it.length > 3) {
                viewModel.getNewsByQuery(it, Country.COUNTRY)
            }
        }
        return true
    }

    override fun onGlobalLayout() {
        val r = Rect()
        binding.root.getWindowVisibleDisplayFrame(r)
        val screenHeight: Int = binding.root.getRootView().getHeight()

        // r.bottom is the position above soft keypad or device button.
        // if keypad is shown, the r.bottom is smaller than that before.
        val keypadHeight = screenHeight - r.bottom
        Log.d(TAG, "keypadHeight = $keypadHeight")
        if (keypadHeight > screenHeight * 0.15) { // 0.15 ratio is perhaps enough to determine keypad height.
            // keyboard is opened
            if (!isKeyboardShowing) {
                isKeyboardShowing = true
                onKeyboardVisibilityChanged(true)
            }
        } else {
            // keyboard is closed
            if (isKeyboardShowing) {
                isKeyboardShowing = false
                onKeyboardVisibilityChanged(false)
            }
        }

    }


}