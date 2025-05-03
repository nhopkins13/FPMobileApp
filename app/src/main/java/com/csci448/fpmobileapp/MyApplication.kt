package com.csci448.fpmobileapp // Use your actual package name

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class MyApplication : Application() {

    companion object {
        const val TASK_REMINDER_CHANNEL_ID = "TASK_REMINDER_CHANNEL"
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.task_reminder_channel_name) // Add this string resource
            val descriptionText = getString(R.string.task_reminder_channel_description) // Add this string resource
            val importance = NotificationManager.IMPORTANCE_HIGH // High importance for reminders
            val channel = NotificationChannel(TASK_REMINDER_CHANNEL_ID, name, importance).apply {
                description = descriptionText
                // Optional: Configure lights, vibration, etc.
                // enableLights(true)
                // lightColor = Color.RED
                // enableVibration(true)
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}