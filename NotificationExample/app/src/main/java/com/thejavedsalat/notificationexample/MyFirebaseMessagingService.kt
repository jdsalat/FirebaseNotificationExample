package com.thejavedsalat.notificationexample

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.support.v7.app.NotificationCompat
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

/**
 * Created by Javed.Salat on 10/19/2017.
 */
class MyFirebaseMessagingService : FirebaseMessagingService() {

    val TAG = MyFirebaseMessagingService::class.java.simpleName
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)

        Log.d(TAG, "From:" + remoteMessage!!.from)
        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "Message data payload:" + remoteMessage!!.data)
        }

        if (remoteMessage.notification != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.notification.body!!);
            displayNotification(remoteMessage)
        }
    }

    private fun displayNotification(remoteMessage: RemoteMessage?) {
        val intentValue = Intent(this, ViewNotificationActivity::class.java)
        intentValue.putExtra("title", getString(R.string.notification_title))
        intentValue.putExtra("description", remoteMessage!!.notification.body)

        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intentValue, PendingIntent.FLAG_UPDATE_CURRENT)

        val defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder = NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(remoteMessage!!.notification.body)
                .setSound(defaultSound)
                .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())
    }
}