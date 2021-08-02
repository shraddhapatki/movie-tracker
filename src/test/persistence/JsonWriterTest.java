package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    public void testInvalidFile() {
        try {
            MovieList movieList = new MovieList("My Movie List");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("Expected exception thrown");
        } catch (IOException e) {
        }
    }

    @Test
    public void testEmptyMovieList() {
        try {
            MovieList movieList = new MovieList("My Movie List");
            JsonWriter writer = new JsonWriter("./data/testEmptyMovieList.json");
            writer.open();
            writer.write(movieList);
            writer.close();
            JsonReader reader = new JsonReader("./data/testEmptyMovieList.json");
            movieList = reader.read();
            assertEquals("My Movie List", movieList.getName());
        } catch (IOException e) {
            fail("Unexpected exception thrown");
        }
    }

    @Test
    public void testMovieList() {
        try {
            MovieList movieList = new MovieList("My Movie List");
            movieList.addAll(new Movie("life of pi", "adventure", 8));
            movieList.addAll(new Movie("goodfellas", "drama", 9));
            JsonWriter writer = new JsonWriter("./data/testMovieList.json");
            writer.open();
            writer.write(movieList);
            writer.close();
            JsonReader reader = new JsonReader("./data/testMovieList.json");
            movieList = reader.read();
            assertEquals("My Movie List", movieList.getName());
            List<Movie> movies = movieList.getAllMovies();
            checkMovie(movies.get(0), "life of pi", "adventure", 8);
            checkMovie(movies.get(1), "goodfellas", "drama", 9);
        } catch (IOException e) {
            fail("Unexpected exception thrown");
        }
    }

    @Test
    public void testWatchList() {
        try {
            MovieList movieList = new MovieList("My Movie List");
            movieList.addAll(new Movie("life of pi", "adventure", 8));
            movieList.addAll(new Movie("goodfellas", "drama", 9));
            movieList.addWatched(new Movie("life of pi", "adventure", 8));
            movieList.addUnwatched(new Movie("goodfellas", "drama", 9));
            JsonWriter writer = new JsonWriter("./data/testWatchedList.json");
            writer.open();
            writer.write(movieList);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWatchedList.json");
            movieList = reader.read();
            List<Movie> watchedMovies = movieList.getWatchedMovies();
            checkMovie(watchedMovies.get(0), "life of pi","adventure", 8);
            List<Movie> unwatchedMovies = movieList.getUnwatchedMovies();
            checkMovie(unwatchedMovies.get(0), "goodfellas", "drama", 9);
        } catch (IOException e) {
            fail("Unexpected exception thrown");
        }
    }
}
