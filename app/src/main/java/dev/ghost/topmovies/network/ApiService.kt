package dev.ghost.topmovies.network

import dev.ghost.topmovies.dao.MovieDao
import dev.ghost.topmovies.entities.Movie
import dev.ghost.topmovies.response.ResponseMovies
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

//    @POST("login")
//    @FormUrlEncoded
//    fun loginPostAsync(
//        @Field("email") email: String,
//        @Field("password") password: String
//    ): Deferred<Response<User>>
//
//    @POST("register")
//    @FormUrlEncoded
//    fun registerPostAsync(
//        @Field("email") email: String,
//        @Field("name") name: String,
//        @Field("password") password: String
//    ): Deferred<Response<User>>
//
    @GET("?api_key=2ba4c2cc3a41192b215410b0f7814612&sort_by=popularity.desc&page=1&primary_release_year=2019")
    @Headers("Accept:application/json")
    fun getMoviesAsync(): Deferred<Response<ResponseMovies>>

//    @GET("priorities")
//    @Headers("Accept:application/json")
//    fun getPrioritiesAsync(@Query("api_token") token: String): Deferred<List<Priority>>
//
//    @GET("categories")
//    @Headers("Accept:application/json")
//    fun getCategoriesAsync(@Query("api_token") token: String): Deferred<List<Category>>
//
//    @POST("tasks")
//    fun addTaskAsync(
//        @Query("api_token") token: String,
//        @Body task: Task
//    ): Deferred<Response<Task>>
//
//    @PATCH("tasks/{id}")
//    @Headers("Accept:application/json")
//    fun patchTaskAsync(
//        @Path("id") id:Int,
//        @Query("api_token") token: String,
//        @Body task: Task
//    ): Deferred<Response<Task>>
//
//    @DELETE("tasks/{id}")
//    @Headers("Accept:application/json")
//    fun deleteTaskAsync(
//        @Path("id") id:Int,
//        @Query("api_token") token: String
//    ): Deferred<Response<DeleteTaskResponse>>
//
//    @POST("categories")
//    fun addCategoryAsync(
//        @Query("api_token") token: String,
//        @Body category: Category
//    ): Deferred<Response<Category>>

}