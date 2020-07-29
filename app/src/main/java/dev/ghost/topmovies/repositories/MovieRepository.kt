package dev.ghost.topmovies.repositories

import android.util.Log
import dev.ghost.topmovies.dao.MovieDao
import dev.ghost.topmovies.network.ApiService
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.withContext
import java.lang.Exception

class MovieRepository(
    private val apiService: ApiService,
    private val movieDao: MovieDao
) {
    val allMovies = movieDao.getAll()

    suspend fun refresh() {
        withContext(Dispatchers.IO) {
            val responseMovies = apiService.getMoviesAsync().await()
            if (responseMovies.isSuccessful)
            {
                val responseBody = responseMovies.body()
                responseBody?.movies?.forEach { movie ->
                    try {
                        movieDao.add(movie)
                    } catch (ex: Exception) {
                        Log.e("ADD MOVIE TO DB ERROR", ex.message + " " + movie.toString())
                    }
                }
            }
            else
            {
                val errorContent = responseMovies.errorBody()?.string()
                throw Exception(errorContent)
            }
        }
    }
}