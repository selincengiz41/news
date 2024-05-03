package com.selincengiz.news.presentation.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.selincengiz.news.R
import com.selincengiz.news.databinding.FragmentNotificationBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NotificationFragment : Fragment() {

    private lateinit var binding: FragmentNotificationBinding
    private val adapterNotification by lazy { NotificationAdapter() }
    private val viewModel by viewModels<NotificationViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_notification, container, false)
        binding.notificationRecycler.adapter = adapterNotification
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getNotifications()
        observe()

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

    }

    fun observe() {
        viewModel.notifications.observe(viewLifecycleOwner) {
            adapterNotification.submitList(it)
        }
    }


}