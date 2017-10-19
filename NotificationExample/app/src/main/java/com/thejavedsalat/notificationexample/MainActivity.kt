package com.thejavedsalat.notificationexample

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.NotificationCompat
import android.view.View
import android.widget.Button
import android.widget.RemoteViews
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnNotification = findViewById(R.id.btn_notification) as Button
        val btnCustomNotification = findViewById(R.id.btn_custom_notification) as Button

        btnNotification.setOnClickListener(this)
        btnCustomNotification.setOnClickListener(this)

    }

    override fun onClick(view: View?) {


        when {
            view!!.id === R.id.btn_notification -> displayNotification()
            view!!.id === R.id.btn_custom_notification -> displayCustomNotification()
            else -> Toast.makeText(this, "Please click the button", Toast.LENGTH_SHORT).show()

        }
    }


    private fun displayNotification() {
        val intentValue = Intent(this, ViewNotificationActivity::class.java)
        intentValue.putExtra("title", getString(R.string.notification_title))
        intentValue.putExtra("description", getString(R.string.notification_message))

        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intentValue, PendingIntent.FLAG_UPDATE_CURRENT)

        val defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder = NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_message))
                .setSound(defaultSound)
                .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())
    }

    private fun displayCustomNotification() {

        val remoteViews = RemoteViews(packageName, R.layout.notification_custom)

        val intentValue = Intent(this, ViewNotificationActivity::class.java)
        intentValue.putExtra("title", getString(R.string.notification_title))
        intentValue.putExtra("description", getString(R.string.notification_message))
        val pIntent = PendingIntent.getActivity(this, 0, intentValue, PendingIntent.FLAG_UPDATE_CURRENT)
        val defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        var bitmapImage = BitmapFactory.decodeResource(resources, R.drawable.ic_notification)
        val notificationBuilder = NotificationCompat.Builder(this)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_message))
               // .setContent(remoteViews)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentIntent(pIntent)
                .setSound(defaultSound)
                .setTicker(getString(R.string.notification_message))
                .setStyle(android.support.v4.app.NotificationCompat.BigPictureStyle().bigPicture(bitmapImage).setSummaryText(getString(R.string.notification_message)))


        // remoteViews.setImageViewResource(R.id.imageView, R.drawable.ic_notification)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())
    }


}
