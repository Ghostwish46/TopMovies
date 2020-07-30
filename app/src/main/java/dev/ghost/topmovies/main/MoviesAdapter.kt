package dev.ghost.topmovies.main

import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.ghost.topmovies.R
import dev.ghost.topmovies.databinding.ItemMovieBinding
import dev.ghost.topmovies.entities.Movie
import dev.ghost.topmovies.helpers.SchedulingAlarmManager
import dev.ghost.topmovies.receivers.SchedulingReceiver
import kotlinx.android.synthetic.main.item_movie.view.*
import java.util.*
import kotlin.math.min


class MoviesAdapter :
    PagedListAdapter<Movie, MoviesAdapter.MovieViewHolder>(MOVIES_COMPARATOR) {

    companion object {
        // Comparator for movies distinction.
        private val MOVIES_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldMovie: Movie, newMovie: Movie): Boolean {
                return oldMovie.title == newMovie.title
                        && oldMovie.overview == newMovie.overview
                        && oldMovie.releaseDate == newMovie.releaseDate
                        && oldMovie.posterPath == newMovie.posterPath
                        && oldMovie.notificationTime == newMovie.notificationTime
            }
        }
    }

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.movie = movie;
            binding.executePendingBindings();
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val movieBinding: ItemMovieBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_movie, parent, false
            )
        return MovieViewHolder(movieBinding)
    }

    // Set binding for current view holder.
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentMovie = getItem(position)
        currentMovie?.let {
            holder.bind(currentMovie)

            holder.itemView.buttonItemMovieScheduleViewing.setOnClickListener {
                val calendar = GregorianCalendar()

                lateinit var scheduledDate: GregorianCalendar

                val dpd = DatePickerDialog(
                    holder.itemView.context,
                    DatePickerDialog.OnDateSetListener { dialog, year, monthOfYear, dayOfMonth ->

                        scheduledDate = GregorianCalendar(year, monthOfYear, dayOfMonth)

                        val tpd = TimePickerDialog(
                            holder.itemView.context,
                            TimePickerDialog.OnTimeSetListener { timePicker, hours, minutes ->
                                scheduledDate.set(Calendar.HOUR_OF_DAY, hours)
                                scheduledDate.set(Calendar.MINUTE, minutes)

                                currentMovie.notificationTime = scheduledDate.timeInMillis

                                SchedulingAlarmManager().setScheduling(
                                    holder.itemView.context.applicationContext,
                                    currentMovie
                                )
                            },
                            calendar.get(Calendar.HOUR_OF_DAY),
                            calendar.get(Calendar.MINUTE),
                            true
                        )
                        tpd.show()
                    }, calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                )
                dpd.show()
            }
        }
    }
}