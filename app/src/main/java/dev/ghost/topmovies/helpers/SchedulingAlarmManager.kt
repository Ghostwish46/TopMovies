package dev.ghost.topmovies.helpers

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import dev.ghost.topmovies.R
import dev.ghost.topmovies.entities.Movie
import dev.ghost.topmovies.main.MainActivity
import dev.ghost.topmovies.receivers.SchedulingReceiver
import java.util.*

// Alarm Manager for scheduling notifications.
class SchedulingAlarmManager {
    companion object {
        const val ALARM_ACTION = "dev.ghost.topmovies.MOVIE_ALARM"
        const val BUNDLE = "Bundle"
    }

    fun setScheduling(context: Context, movie: Movie) {
        val intent = Intent(context, SchedulingReceiver::class.java)
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
        intent.action = ALARM_ACTION
        intent.data =  Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME))

        val bundle = Bundle()
        bundle.putParcelable(MainActivity.MOVIE, movie)
        intent.putExtra(BUNDLE, bundle)

        val pendingIntent = PendingIntent.getBroadcast(
            context, movie.id, intent,
            0
        )

        val alarmManager = context
            .getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val triggeringCalendar = GregorianCalendar()
        triggeringCalendar.time = Date(movie.notificationTime)

        alarmManager.set(
            AlarmManager.RTC_WAKEUP,
            triggeringCalendar.timeInMillis,
            pendingIntent
        )



    }
}