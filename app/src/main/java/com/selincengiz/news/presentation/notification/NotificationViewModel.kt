package com.selincengiz.news.presentation.notification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.selincengiz.news.common.HomeState
import com.selincengiz.news.domain.entities.Notification
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(private val firestore: FirebaseFirestore) : ViewModel() {

    private var _notifications = MutableLiveData<List<Notification>>()
    val notifications: LiveData<List<Notification>>
        get() = _notifications

    fun getNotifications() {
        firestore.collection("notifications")
            .addSnapshotListener { snapshot, error ->

                val tempList = mutableListOf<Notification>()

                snapshot?.forEach { document ->
                    tempList.add(
                        Notification(
                            document.get("title") as String,
                            document.get("desc") as String
                        )
                    )
                }
                _notifications.value = tempList

            }
    }

}