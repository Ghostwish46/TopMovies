package dev.ghost.topmovies.main

import android.app.Application
import androidx.lifecycle.*
import dev.ghost.topmovies.entities.Movie
import dev.ghost.topmovies.helpers.AppDatabase
import dev.ghost.topmovies.network.ApiService
import dev.ghost.topmovies.network.LoadingState
import dev.ghost.topmovies.repositories.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val _loadingState = MutableLiveData<LoadingState>()

    private val movieRepository: MovieRepository
    val moviesAdapter: MoviesAdapter

    val moviesData: LiveData<List<Movie>>

    init {
        val appDatabase = AppDatabase.getDatabase(application)
        val apiService = ApiUtils.apiService

        moviesAdapter = MoviesAdapter()
        movieRepository = MovieRepository(apiService, appDatabase.movieDao)
        moviesData = movieRepository.allMovies
        fetchTasks()
    }

    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    private fun fetchTasks() {
        viewModelScope.launch {
            try {
                _loadingState.value = LoadingState.LOADING
                movieRepository.refresh()
                _loadingState.value = LoadingState.LOADED
            }
            catch (ex:Exception)
            {
                _loadingState.value = LoadingState.error(ex.message)
            }
        }
    }
}