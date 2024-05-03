package com.selincengiz.news.common

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.selincengiz.news.R
import com.selincengiz.news.presentation.MainActivity

object NotificationUtils {

    fun showNotification(context: Context,title:String,desc:String){
        val builder:NotificationCompat.Builder

        val notificationManager =context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val intent = Intent(context, MainActivity::class.java)

        val pendingIntent= PendingIntent.getActivity(context,1,intent,PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        val channelId ="CHANNEL_ID"
        val channelName ="CHANNEL_NAME"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel =NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            )

            notificationManager.createNotificationChannel(channel)

            builder =NotificationCompat.Builder(context,channelId)
                .setContentTitle(title)
                .setContentText(desc)
                .setSmallIcon(R.drawable.logo)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
        }

        else{
            builder =NotificationCompat.Builder(context)
                .setContentTitle(title)
                .setContentText(desc)
                .setSmallIcon(R.drawable.logo)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
        }

        notificationManager.notify(1,builder.build())
    }
}