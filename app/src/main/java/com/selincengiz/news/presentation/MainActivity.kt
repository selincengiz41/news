package com.selincengiz.news.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.selincengiz.news.R
import com.selincengiz.news.common.Keyboard.isKeyboardShowing
import com.selincengiz.news.databinding.ActivityMainBinding
import com.selincengiz.news.presentation.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        bottomNav()
    }


    fun bottomNav() {
        with(binding) {

            //Bottom Navigation Menu
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment


            NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.navController)
            navHostFragment.navController.addOnDestinationChangedListener { controller, destination, _ ->


                when (destination.id) {


                    R.id.homeFragment -> {
                        containerBottomNavigationView.visibility = View.VISIBLE

                    }


                    R.id.favoriteFragment -> {
                        containerBottomNavigationView.visibility = View.VISIBLE


                    }


                    R.id.profileFragment -> {
                        containerBottomNavigationView.visibility = View.VISIBLE


                    }

                    else -> {
                        containerBottomNavigationView.visibility = View.GONE


                    }

                }


            }
        }
    }
}