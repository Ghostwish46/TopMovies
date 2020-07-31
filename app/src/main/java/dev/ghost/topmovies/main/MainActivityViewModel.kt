package dev.ghost.topmovies.main

import android.app.Application
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import androidx.lifecycle.*
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import dev.ghost.topmovies.dataSources.MovieDataSource
import dev.ghost.topmovies.entities.Movie
import dev.ghost.topmovies.network.LoadingState
import java.util.*

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    companion object{
        const val PAGE_SIZE = 20
    }

    private val moviesData: LiveData<PagedList<Movie>>
    private val loadingState = MutableLiveData<LoadingState>()
    lateinit var moviesAdapter: MoviesAdapter

    init {
        // Initial config params for paged list.
        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()
        moviesData = initializedPagedListBuilder(config).build()
    }

    fun getMovies(): LiveData<PagedList<Movie>> = moviesData
    fun getLoadingStates(): LiveData<LoadingState> = loadingState

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<Int, Movie> {

        val dataSourceFactory = object : DataSource.Factory<Int, Movie>() {
            override fun create(): DataSource<Int, Movie> {
                return MovieDataSource(viewModelScope, loadingState)
            }
        }
        return LivePagedListBuilder<Int, Movie>(dataSourceFactory, config)
    }
}