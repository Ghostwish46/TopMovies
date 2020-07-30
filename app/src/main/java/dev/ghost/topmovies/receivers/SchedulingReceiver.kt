package dev.ghost.topmovies.receivers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.view.View
import android.widget.Toast
import androidx.core.app.NotificationCompat
import dev.ghost.topmovies.App
import dev.ghost.topmovies.R
import dev.ghost.topmovies.entities.Movie
import dev.ghost.topmovies.helpers.SchedulingAlarmManager
import dev.ghost.topmovies.main.MainActivity

// Broadcast receiver for notifications creation
// (depends on Alarm Manager scheduling).
class SchedulingReceiver : BroadcastReceiver() {

    // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
    override fun onReceive(context: Context, intent: Intent) {

        val bundle = intent.getBundleExtra(SchedulingAlarmManager.BUNDLE)
        val currentMovie: Movie? = bundle?.getParcelable(MainActivity.MOVIE)

        currentMovie?.let {
            val builder = NotificationCompat
                .Builder(context, App.CHANNEL_ID)
                .setSmallIcon(R.drawable.icon_placeholder)
                .setContentTitle(currentMovie.title)
                .setContentText(currentMovie.overview)
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText(currentMovie.overview)
                )
                .setVibrate(longArrayOf(500, 500, 500, 500))
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setPriority(NotificationCompat.PRIORITY_MAX)

            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = context.getString(R.string.channel_name)
                val descriptionText = context.getString(R.string.channel_description)
                val importance = NotificationManager.IMPORTANCE_HIGH
                val channel = NotificationChannel(App.CHANNEL_ID, name, importance).apply {
                    description = descriptionText
                }
                channel.lockscreenVisibility = View.VISIBLE
                channel.setShowBadge(true)
                channel.vibrationPattern = longArrayOf(500, 500, 500, 500)
                // Register the channel with the system
                notificationManager.createNotificationChannel(channel)
            }

            notificationManager.notify(currentMovie.id, builder.build())
        }
    }
}
