package dev.ghost.topmovies

import dev.ghost.topmovies.entities.Movie
import dev.ghost.topmovies.entities.RatingType
import org.junit.Test

import org.junit.Assert.*

class MovieTypeUnitTest {
    @Test
    fun movieType_80_Is_High_ReturnsTrue()
    {
        val movie = Movie(
            1, "Test title", "Test overview",
            "2019-10-16", 8.0
        )

        assertEquals(movie.getMovieRating(), RatingType.HIGH)
    }

    @Test
    fun movieType_50_Is_Medium_ReturnsTrue()
    {
        val movie = Movie(
            1, "Test title", "Test overview",
            "2019-10-16", 5.0
        )

        assertEquals(movie.getMovieRating(), RatingType.MEDIUM)
    }

    @Test
    fun movieType_20_Is_Low_ReturnsTrue()
    {
        val movie = Movie(
            1, "Test title", "Test overview",
            "2019-10-16", 2.0
        )

        assertEquals(movie.getMovieRating(), RatingType.LOW)
    }

    @Test
    fun movieType_Minus_10_Is_Low_ReturnsTrue()
    {
        val movie = Movie(
            1, "Test title", "Test overview",
            "2019-10-16", -1.0
        )

        assertEquals(movie.getMovieRating(), RatingType.LOW)
    }
}