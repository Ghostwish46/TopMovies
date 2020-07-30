package dev.ghost.topmovies

import dev.ghost.topmovies.entities.Movie
import org.junit.Test
import org.junit.Assert.*

class RatingFormatterUnitTest {

    @Test
    fun ratingFormatter_CorrectRating_10_Returns_True()
    {
        val movie = Movie(
            1, "Test title", "Test overview",
            "2019-10-16", 6.6
        )

        assertEquals(movie.getFormattedRating(), 66)
    }

    // Failed test with parser behaviour.
    @Test
    fun ratingFormatter_IncorrectRating_Minus_20_Returns_True()
    {
        val movie = Movie(
            1, "Test title", "Test overview",
            "2019-10-16", -20.00
        )

        assertEquals(movie.getFormattedRating(), 0)
    }
}