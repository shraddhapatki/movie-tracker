package persistence;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/nonExistentFile.json");
        try {
            MovieList movieList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testEmptyMovieList() {
        JsonReader reader = new JsonReader("./data/testEmptyMovieList.json");
        try {
            MovieList movieList = reader.read();
            assertEquals("My Movie List", movieList.getName());
        } catch (IOException e) {
            fail("Can't read from file");
        }
    }

    @Test
    public void testMovieList() {
        JsonReader reader = new JsonReader("./data/testMovieList.json");
        try {
            MovieList movieList = reader.read();
            assertEquals("My Movie List", movieList.getName());
            List<Movie> movies = movieList.getAllMovies();
            checkMovie(movies.get(0), "life of pi", "adventure", 8);
            checkMovie(movies.get(1), "goodfellas", "drama", 9);
        } catch (IOException e) {
            fail("Can't read from file");
        }
    }

    @Test
    public void testWatchList() {
        JsonReader reader = new JsonReader("./data/testWatchedList.json");
        try {
            MovieList movieList = reader.read();
            assertEquals("My Movie List", movieList.getName());
            List<Movie> watched = movieList.getWatchedMovies();
            List<Movie> unwatched = movieList.getUnwatchedMovies();
            checkMovie(watched.get(0), "life of pi", "adventure", 8);
            checkMovie(unwatched.get(0), "goodfellas", "drama", 9);
        } catch (IOException e) {
            fail("Can't read from file");
        }
    }
}
