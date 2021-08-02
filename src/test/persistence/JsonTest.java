package persistence;

import model.Movie;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkMovie(Movie movie, String movieName, String movieGenre, int movieRating) {
        assertEquals(movieName, movie.getName());
        assertEquals(movieRating, movie.getRating());
        assertEquals(movieGenre, movie.getGenre());
    }
}
