package dev.ghost.topmovies.network

import dev.ghost.topmovies.response.ResponseMovies
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    companion object{
        const val API_KEY = "2ba4c2cc3a41192b215410b0f7814612"
    }

    @GET("?api_key=$API_KEY&sort_by=popularity.desc&primary_release_year=2019")
    @Headers("Accept:application/json")
    fun getMoviesAsync(@Query("page") page: Int): Deferred<Response<ResponseMovies>>
}