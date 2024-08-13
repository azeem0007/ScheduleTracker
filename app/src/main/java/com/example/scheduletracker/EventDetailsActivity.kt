package com.example.scheduletracker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class EventDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)

        // Get data from the intent
        val eventName = intent.getStringExtra("eventName")
        val eventDescription = intent.getStringExtra("eventDescription")
        val eventDate = intent.getStringExtra("eventDate")
        val eventTime = intent.getStringExtra("eventTime")

        // Display the event details in a fragment
        if (savedInstanceState == null) {
            val fragment = EventDetailsFragment.newInstance(eventName, eventDescription, eventDate, eventTime)
            supportFragmentManager.beginTransaction()
                .replace(R.id.eventDetailsContainer, fragment)
                .commit()
        }

        // Create a notification with the event details
        createNotification(eventName, eventDescription, eventDate, eventTime)
    }

    private fun createNotification(eventName: String?, eventDescription: String?, eventDate: String?, eventTime: String?) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create a notification channel for Android O and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("eventChannel", "Event Notifications", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        // Create an intent to open the app when the notification is clicked
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        // Build the notification
        val notification = NotificationCompat.Builder(this, "eventChannel")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Event: $eventName")
            .setContentText("Date: $eventDate Time: $eventTime")
            .setStyle(NotificationCompat.BigTextStyle().bigText("Event: $eventName\nDescription: $eventDescription\nDate: $eventDate\nTime: $eventTime"))
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        // Show the notification
        notificationManager.notify(1, notification)
    }
}
