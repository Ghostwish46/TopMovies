package dev.ghost.topmovies.entities

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import dev.ghost.topmovies.R
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat

@Parcelize
@Entity
data class Movie(
    val id: Int = 0,
    val title: String = "",
    val overview: String = "",
    @SerializedName("release_date")
    val releaseDate: String = "",
    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,
    @SerializedName("poster_path")
    val posterPath: String = "",
    var notificationTime: Long = 0
) : Parcelable {

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


    fun getFormattedRating(): Int =
        (voteAverage * 10).toInt()

    fun getMovieRating(): RatingType =
        when (getFormattedRating()) {
            in 40..70 -> RatingType.MEDIUM
            in 70..100 -> RatingType.HIGH
            else -> RatingType.LOW
        }


    fun getRatingMainColor(): String =
        when (getMovieRating()) {
            RatingType.LOW -> "#9e1e4e"
            RatingType.MEDIUM -> "#d1d424"
            RatingType.HIGH -> "#46d079"
        }
    fun getRatingSecondaryColor(): String =
        when (getMovieRating()) {
            RatingType.LOW -> "#409E1E4E"
            RatingType.MEDIUM -> "#40D1D424"
            RatingType.HIGH -> "#4046D079"
        }
}