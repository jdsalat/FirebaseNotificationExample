package com.thejavedsalat.notificationexample

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class ViewNotificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_notification)
        var txtTitle = findViewById(R.id.txt_title_text) as TextView
        var txtDescription = findViewById(R.id.txt_description_text) as TextView

        val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(0)


        val intentValue: Intent = intent
        txtTitle.text = intentValue.getStringExtra("title")
        txtDescription.text = intentValue.getStringExtra("description")


    }
}
