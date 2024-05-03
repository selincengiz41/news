package com.selincengiz.news.service

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.selincengiz.news.common.Notification.notificationList
import com.selincengiz.news.common.NotificationUtils
import com.selincengiz.news.domain.entities.Notification
import com.selincengiz.news.presentation.notification.NotificationFragment

class MessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val title = message.notification?.title
        val desc = message.notification?.body

        if (title != null && desc != null) {
            NotificationUtils.showNotification(this, title, desc)
            notificationList.add(Notification(title, desc))
        }
//Yerel bildirim oluşturma fonksiyonu


    }
}