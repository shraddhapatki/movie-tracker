package model;

import exceptions.ExistingMovieException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MovieListTest {
    private MovieList testMovieList;
    private Movie testMovie1;
    private Movie testMovie2;
    private Movie testMovie3;

    @BeforeEach
    public void setup() {
        testMovieList = new MovieList("test list");
        testMovie1 = new Movie("Big Fish", "Fantasy", 9);
        testMovie2 = new Movie("Beautiful Boy", "Drama", 7);
        testMovie3 = new Movie("Her", "Sci-Fi", 8);
    }

    @Test
    public void testAddOneMovie() {
        try {
            List<Movie> testList = new ArrayList<>();
            testList.add(testMovie1);
            testMovieList.addAll(testMovie1);
            assertEquals(testList, testMovieList.getAllMovies());
        } catch (ExistingMovieException e) {
            fail("Unexpected exception");
        }
    }

    @Test
    public void testAddMultipleMovies() {
        try {
            List<Movie> testList = new ArrayList<>();
            testList.add(testMovie1);
            testList.add(testMovie2);
            testList.add(testMovie3);
            testMovieList.addAll(testMovie1);
            testMovieList.addAll(testMovie2);
            testMovieList.addAll(testMovie3);
            assertEquals(testList, testMovieList.getAllMovies());
        } catch (ExistingMovieException e) {
            fail("Unexpected exception");
        }
    }

    @Test
    public void testExistingMovies() {
        List<Movie> testList = new ArrayList<>();
        testList.add(testMovie1);
        try {
            testMovieList.addAll(testMovie1);
            testMovieList.addAll(testMovie1);
            fail("Exception thrown");
        } catch (ExistingMovieException e) {
            assertEquals(testList, testMovieList.getAllMovies());
        }
    }

    @Test
    public void testRemoveMovie1() {
        try {
            List<Movie> testList = new ArrayList<>();
            assertEquals(testList, testMovieList.getWatchedMovies());
            testList.add(testMovie2);
            testMovieList.addAll(testMovie1);
            testMovieList.addAll(testMovie2);
            testMovieList.markWatchedMovie("Big Fish");
            testMovieList.removeMovie("Big Fish");
            assertEquals(testList, testMovieList.getAllMovies());
        } catch (ExistingMovieException e) {
            fail("Unexpected exception");
        }
    }

    @Test
    public void testRemoveMovie2() {
        try {
            List<Movie> testList = new ArrayList<>();
            testMovieList.addAll(testMovie1);
            testMovieList.addAll(testMovie2);
            testMovieList.markUnwatchedMovie("Big Fish");
            testMovieList.removeMovie("Big Fish");
            assertEquals(testList, testMovieList.getUnwatchedMovieNames());
        } catch (ExistingMovieException e) {
            fail("Unexpected exception");
        }
    }

    @Test
    public void testRemoveMovie3() {
        try {
            List<Movie> testList = new ArrayList<>();
            testMovieList.addAll(testMovie1);
            testMovieList.addAll(testMovie2);
            testMovieList.removeMovie("Her");
            assertEquals(testList, testMovieList.getUnwatchedMovies());
            assertEquals(testList, testMovieList.getWatchedMovies());
            testList.add(testMovie1);
            testList.add(testMovie2);
            assertEquals(testList, testMovieList.getAllMovies());
        } catch (ExistingMovieException e) {
            fail("Unexpected exception");
        }
    }

    @Test
    public void testMarkWatched() {
        try {
            testMovieList.addAll(testMovie1);
            testMovieList.addAll(testMovie2);
            testMovieList.addAll(testMovie3);
            testMovieList.markUnwatchedMovie("Her");
            testMovieList.markWatchedMovie("Her");
            List<Movie> testList = new ArrayList<>();
            testList.add(testMovie3);
            assertEquals(testList, testMovieList.getWatchedMovies());
            List<Movie> allList = new ArrayList<>();
            allList.add(testMovie1);
            allList.add(testMovie2);
            allList.add(testMovie3);
            assertEquals(allList, testMovieList.getAllMovies());
        } catch (ExistingMovieException e) {
            fail("Unexpected exception");
        }

    }

    @Test
    public void testMarkWatched2() {
        try {
            testMovieList.addAll(testMovie1);
            testMovieList.markWatchedMovie("Big Fish");
            List<Movie> testList = new ArrayList<>();
            testList.add(testMovie1);
            assertEquals(testList, testMovieList.getWatchedMovies());
        } catch (ExistingMovieException e) {
            fail("Unexpected exception");
        }
    }

    @Test
    public void testMarkWatched3() {
        try {
            testMovieList.addWatched(testMovie1);
            testMovieList.addAll(testMovie1);
            testMovieList.markWatchedMovie("Big Fish");
            List<Movie> testList = new ArrayList<>();
            testList.add(testMovie1);
            assertEquals(testList, testMovieList.getAllMovies());
            assertEquals(testList, testMovieList.getWatchedMovies());
        } catch (ExistingMovieException e) {
            fail("Unexpected exception");
        }
    }

    @Test
    public void testMarkUnwatched() {
        try {
            testMovieList.addAll(testMovie1);
            testMovieList.addAll(testMovie2);
            testMovieList.markWatchedMovie("Big Fish");
            testMovieList.markUnwatchedMovie("Big Fish");
            List<Movie> testList = new ArrayList<>();
            testList.add(testMovie1);
            assertEquals(testList, testMovieList.getUnwatchedMovies());
            List<Movie> allMovies = new ArrayList<>();
            allMovies.add(testMovie1);
            allMovies.add(testMovie2);
            assertEquals(allMovies, testMovieList.getAllMovies());
        } catch (ExistingMovieException e) {
            fail("Unexpected exception");
        }
    }

    @Test
    public void testMarkUnwatched2() {
        try {
            testMovieList.addAll(testMovie1);
            testMovieList.markUnwatchedMovie("Big Fish");
            List<Movie> testList = new ArrayList<>();
            testList.add(testMovie1);
            assertEquals(testList, testMovieList.getUnwatchedMovies());
        } catch (ExistingMovieException e) {
            fail("Unexpected exception");
        }
    }

    @Test
    public void testMarkUnwatched3() {
        try {
            testMovieList.addUnwatched(testMovie1);
            testMovieList.addAll(testMovie1);
            testMovieList.markUnwatchedMovie("Big Fish");
            List<Movie> testList = new ArrayList<>();
            testList.add(testMovie1);
            assertEquals(testList, testMovieList.getAllMovies());
            assertEquals(testList, testMovieList.getUnwatchedMovies());
        } catch (ExistingMovieException e) {
            fail("Unexpected exception");
        }
    }

    @Test
    public void testGetAllMovieNames() {
        try {
            testMovieList.addAll(testMovie3);
            testMovieList.addAll(testMovie1);
            List<String> names = new ArrayList<>();
            names.add("Her");
            names.add("Big Fish");
            assertEquals(names, testMovieList.getAllMovieNames());
        } catch (ExistingMovieException e) {
            fail("Unexpected exception");
        }
    }

    @Test
    public void testGetAllWatchedNames() {
        try {
            testMovieList.addAll(testMovie3);
            testMovieList.addAll(testMovie1);
            testMovieList.markWatchedMovie("Her");
            List<String> names = new ArrayList<>();
            names.add("Her");
            assertEquals(names, testMovieList.getWatchedMovieNames());
        } catch (ExistingMovieException e) {
            fail("Unexpected exception");
        }
    }

    @Test
    public void testGetAllUnwatchedNames() {
        try {
            testMovieList.addAll(testMovie3);
            testMovieList.addAll(testMovie1);
            testMovieList.markUnwatchedMovie("Her");
            List<String> names = new ArrayList<>();
            names.add("Her");
            assertEquals(names, testMovieList.getUnwatchedMovieNames());
        } catch (ExistingMovieException e) {
            fail("Unexpected exception");
        }
    }

    @Test
    public void testExistingWatched() {
        List<Movie> testList = new ArrayList<>();
        testList.add(testMovie1);
        try {
            testMovieList.addWatched(testMovie1);
            testMovieList.addWatched(testMovie1);
            fail("Exception thrown");
        } catch (ExistingMovieException e) {
            assertEquals(testList, testMovieList.getWatchedMovies());
        }
    }

    @Test
    public void testExistingUnwatched() {
        List<Movie> testList = new ArrayList<>();
        testList.add(testMovie1);
        try {
            testMovieList.addUnwatched(testMovie1);
            testMovieList.addUnwatched(testMovie1);
            fail("Exception thrown");
        } catch (ExistingMovieException e) {
            assertEquals(testList, testMovieList.getUnwatchedMovies());
        }
    }
}