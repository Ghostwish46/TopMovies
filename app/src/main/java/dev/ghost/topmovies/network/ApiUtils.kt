package dev.ghost.topmovies.network

object ApiUtils {

    private const val BASE_URL = "https://api.themoviedb.org/3/discover/movie/"

    val apiService: ApiService
        get() = RetrofitClient.getClient(BASE_URL)!!.create(ApiService::class.java)
}