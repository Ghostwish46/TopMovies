package dev.ghost.topmovies.response

import com.google.gson.annotations.SerializedName
import dev.ghost.topmovies.entities.Movie

data class ResponseMovies(
    val page: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("results")
    val movies: List<Movie>
) {
}