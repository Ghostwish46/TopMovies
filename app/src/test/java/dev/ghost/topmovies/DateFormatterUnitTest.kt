package dev.ghost.topmovies

import dev.ghost.topmovies.entities.Movie
import org.junit.Test

import org.junit.Assert.*

class DateFormatterUnitTest {
    @Test
    fun dateFormatter_CorrectDate_2019_10_16_ReturnsTrue() {
        val movie = Movie(
            1, "Test title", "Test overview",
            "2019-10-16", 6.6
        )

        assertEquals(movie.getFormattedDate(), "October 16, 2019")
    }

    @Test
    fun dateFormatter_IncorrectDate_SomeLetters_ReturnsTrue() {
        val movie = Movie(
            1, "Test title", "Test overview",
            "SomeLetters", 6.6
        )

        assertEquals(movie.getFormattedDate(), "SomeLetters")
    }


    // Failed test with parser behaviour.
    @Test
    fun dateFormatter_IncorrectDate_2019_15_82_ReturnsTrue() {
        val movie = Movie(
            1, "Test title", "Test overview",
            "2019-15-82", 6.6
        )

        assertEquals(movie.getFormattedDate(), "2019-15-82")
    }
}