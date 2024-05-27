package com.uuranus.myschedule

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.getData().isNotEmpty()) {
            sendNotification(remoteMessage)
        }
    }

    private fun sendNotification(remoteMessage: RemoteMessage) {
        val title = remoteMessage.getData()["title"]
        val message = remoteMessage.getData()["message"]
        val CHANNEL_ID = "ChannerID"
        val mManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val CHANNEL_NAME = "ChannerName"
            val CHANNEL_DESCRIPTION = "ChannerDescription"
            val importance = NotificationManager.IMPORTANCE_HIGH

            // add in API level 26
            val mChannel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance)
            mChannel.description = CHANNEL_DESCRIPTION
            mChannel.enableLights(true)
            mChannel.enableVibration(true)
            mChannel.setVibrationPattern(longArrayOf(100, 200, 100, 200))
            mChannel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
            mManager.createNotificationChannel(mChannel)
        }
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)  // You can change this to your app's icon
            .setAutoCancel(true)
            .setDefaults(Notification.DEFAULT_ALL)
            .setWhen(System.currentTimeMillis())
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            builder.setVibrate(longArrayOf(500, 500))
        }
//        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
//        //builder.setSmallIcon(R.drawable.ic_launcher_background)
//        builder.setAutoCancel(true)
//        builder.setDefaults(Notification.DEFAULT_ALL)
//        builder.setWhen(System.currentTimeMillis())
//        //builder.setSmallIcon(R.mipmap.ic_launcher)
//        builder.setContentTitle(title)
//        builder.setContentText(message)
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
//            builder.setContentTitle(title)
//            builder.setVibrate(longArrayOf(500, 500))
//        }
//        mManager.notify(0, builder.build())
    }

    override fun onNewToken(s: String) {
        super.onNewToken(s)
    }
}