package com.selincengiz.news.presentation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
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

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {
            // FCM SDK (and your app) can post notifications.
        } else {
            // TODO: Inform user that that your app will not show notifications.
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        bottomNav()


    }

    override fun onResume() {
        super.onResume()
        askNotificationPermission()
    }



    private fun askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                // FCM SDK (and your app) can post notifications.
            } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                Toast.makeText(
                    this,
                    "For notifications you should give permission.",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
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
                        floatingActionButton.visibility = View.GONE

                    }


                    R.id.favoriteFragment -> {
                        containerBottomNavigationView.visibility = View.VISIBLE
                        floatingActionButton.visibility = View.GONE


                    }


                    R.id.profileFragment -> {
                        containerBottomNavigationView.visibility = View.VISIBLE
                        floatingActionButton.visibility = View.GONE


                    }

                    R.id.detailFragment -> {
                        containerBottomNavigationView.visibility = View.GONE
                        floatingActionButton.visibility = View.VISIBLE

                    }

                    else -> {
                        containerBottomNavigationView.visibility = View.GONE
                        floatingActionButton.visibility = View.GONE


                    }

                }


            }
        }
    }
}