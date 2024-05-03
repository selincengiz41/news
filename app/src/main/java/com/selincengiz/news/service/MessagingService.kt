package com.selincengiz.news.service

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.selincengiz.news.common.NotificationUtils

class MessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val title = message.notification?.title
        val desc = message.notification?.body

        if (title != null && desc != null) {

        /*    val db = Firebase.firestore
            val notification=Notification(title, desc)
            Log.i("firebase", "${notification.title}")
            db.collection("notifications").document(title)
                .set(notification).addOnSuccessListener {
                    Log.i("firebase", "DocumentSnapshot written")
                }
                .addOnFailureListener { e ->
                    Log.i("firebase", "Error adding document", e)
                }*/

            NotificationUtils.showNotification(this, title, desc)

        }

//Yerel bildirim oluşturma fonksiyonu


    }
}