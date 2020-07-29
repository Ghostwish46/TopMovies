package dev.ghost.topmovies.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.lang.Exception
import java.text.SimpleDateFormat

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,
    @SerializedName("poster_path")
    val posterPath: String,
    val notificationTime: Long = 0
) {

    fun getFormattedDate(): String {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        return try {
            val date = simpleDateFormat.parse(releaseDate)
            if (date != null) {
                SimpleDateFormat("MMMM dd, yyyy").format(date)
            } else
                releaseDate
        } catch (ex: Exception) {
            releaseDate
        }
    }

    fun getFormattedPosterPath(): String =
        "https://image.tmdb.org/t/p/w220_and_h330_face$posterPath"

    @Ignore
    var formattedRating:Int =
        (voteAverage * 10).toInt()
}