package dev.ghost.topmovies.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.ghost.topmovies.entities.Movie

@Dao
interface MovieDao {
    @Query("Select * from movies")
    fun getAll(): LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(movie: Movie)
}