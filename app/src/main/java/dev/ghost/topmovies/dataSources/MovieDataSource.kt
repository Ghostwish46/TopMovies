package dev.ghost.topmovies.dataSources

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import dev.ghost.topmovies.entities.Movie
import dev.ghost.topmovies.network.ApiUtils
import dev.ghost.topmovies.network.LoadingState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

// DataSource for requests with paging.
class MovieDataSource(
    private val scope: CoroutineScope,
    private val loadingState: MutableLiveData<LoadingState>
) : PageKeyedDataSource<Int, Movie>() {
    companion object {
        const val FIRST_PAGE = 1
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        scope.launch {
            try {
                loadingState.value = LoadingState.LOADING
                val responseMovies =
                    ApiUtils.apiService.getMoviesAsync(FIRST_PAGE).await()
                if (responseMovies.isSuccessful) {
                    val responseBody = responseMovies.body()
                    loadingState.value = LoadingState.LOADED
                    callback.onResult(
                        responseBody?.movies ?: listOf(),
                        responseBody?.page?.minus(1), responseBody?.page?.plus(1)
                    )
                }
            } catch (ex: Exception) {
                Log.e("PAGINATION_ERROR", ex.message.toString())
                loadingState.value = LoadingState.error(ex.message)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        scope.launch {
            try {
                loadingState.value = LoadingState.LOADING
                val service =
                    ApiUtils.apiService.getMoviesAsync(params.key).await()
                if (service.isSuccessful) {
                    val responseBody = service.body()
                    loadingState.value = LoadingState.LOADED
                    callback.onResult(
                        responseBody?.movies ?: listOf(),
                        responseBody?.page?.plus(1)
                    )
                }
            } catch (ex: Exception) {
                Log.e("PAGINATION_ERROR", ex.message.toString())
                loadingState.value = LoadingState.error(ex.message)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        scope.launch {
            try {
                loadingState.value = LoadingState.LOADING
                val service =
                    ApiUtils.apiService.getMoviesAsync(params.key).await()
                if (service.isSuccessful) {
                    val responseBody = service.body()
                    loadingState.value = LoadingState.LOADED
                    callback.onResult(
                        responseBody?.movies ?: listOf(),
                        responseBody?.page?.minus(1)
                    )
                }
            } catch (ex: Exception) {
                Log.e("PAGINATION_ERROR", ex.message.toString())
                loadingState.value = LoadingState.error(ex.message)
            }
        }
    }
}