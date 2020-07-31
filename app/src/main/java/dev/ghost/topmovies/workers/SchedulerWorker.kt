package dev.ghost.topmovies.workers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import dev.ghost.topmovies.App
import dev.ghost.topmovies.R
import dev.ghost.topmovies.entities.Movie
import dev.ghost.topmovies.helpers.SchedulingAlarmManager
import dev.ghost.topmovies.main.MainActivity

class SchedulerWorker(context: Context, workerParams: WorkerParameters) : Worker(
    context,
    workerParams
) {
    override fun doWork(): Result {

        val id = inputData.getInt(MainActivity.MOVIE_ID, -1)
        val title = inputData.getString(MainActivity.TITLE)
        val overview = inputData.getString(MainActivity.OVERVIEW)

        if (id > -1 && !title.isNullOrBlank() && !overview.isNullOrBlank())
        {
            val builder = NotificationCompat
                .Builder(applicationContext, App.CHANNEL_ID)
                .setSmallIcon(R.drawable.icon_placeholder)
                .setContentTitle(title)
                .setContentText(overview)
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText(overview)
                )
                .setVibrate(longArrayOf(500, 500, 500, 500))
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setPriority(NotificationCompat.PRIORITY_MAX)

            val notificationManager: NotificationManager =
                applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = applicationContext.getString(R.string.channel_name)
                val descriptionText = applicationContext.getString(R.string.channel_description)
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

            notificationManager.notify(id, builder.build())

            return Result.success()
        }
        else
            return Result.failure()
    }
}