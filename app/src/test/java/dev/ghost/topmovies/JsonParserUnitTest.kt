package dev.ghost.topmovies

import com.google.gson.Gson
import dev.ghost.topmovies.entities.Movie
import org.junit.Test

import org.junit.Assert.*

class JsonParserUnitTest {
    @Test
    fun jsonParser_Correct_Movie_ReturnsTrue() {
        val jsonString = "{\n" +
                "            \"popularity\": 91.445,\n" +
                "            \"vote_count\": 47,\n" +
                "            \"video\": false,\n" +
                "            \"poster_path\": \"/A11Ez4UkOE4Ysmtmur5Bho8qrGM.jpg\",\n" +
                "            \"id\": 611395,\n" +
                "            \"adult\": false,\n" +
                "            \"backdrop_path\": \"/cAfW4ZPsLA6yOapJUSUq3fruCfQ.jpg\",\n" +
                "            \"original_language\": \"zh\",\n" +
                "            \"original_title\": \"征途\",\n" +
                "            \"genre_ids\": [\n" +
                "                28,\n" +
                "                12\n" +
                "            ],\n" +
                "            \"title\": \"Double World\",\n" +
                "            \"vote_average\": 7,\n" +
                "            \"overview\": \"Keen to bring honor to his clan, young villager Dong Yilong embarks on a perilous journey to compete in a tournament that selects warriors for battle.\",\n" +
                "            \"release_date\": \"2019-11-22\"\n" +
                "        }"

        val movie = Movie(
            611395,
            "Double World",
            "Keen to bring honor to his clan, young villager Dong Yilong embarks on a perilous journey to compete in a tournament that selects warriors for battle.",
            "2019-11-22",
            7.0,
            "/A11Ez4UkOE4Ysmtmur5Bho8qrGM.jpg",
            0
        )

        assertEquals(Gson().fromJson(jsonString, Movie::class.java), movie)
    }
}