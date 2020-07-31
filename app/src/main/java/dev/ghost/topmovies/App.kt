package dev.ghost.topmovies

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.view.View

class App : Application() {
    companion object {
        const val CHANNEL_ID = "TopMoviesChannel"
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationService()
    }

    private fun createNotificationService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            channel.lockscreenVisibility = View.VISIBLE
            channel.setShowBadge(true)
            channel.vibrationPattern = longArrayOf(500, 500, 500, 500)
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}