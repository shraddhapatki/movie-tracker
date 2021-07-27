package model;

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
        testMovieList = new MovieList();
        testMovie1 = new Movie("Big Fish", "Fantasy", 9);
        testMovie2 = new Movie("Beautiful Boy", "Drama", 7);
        testMovie3 = new Movie("Her", "Sci-Fi", 8);
    }

    @Test
    public void testAddOneMovie() {
        testMovieList.addMovie(testMovie1);
        List<Movie> testList = new ArrayList<>();
        testList.add(testMovie1);
        assertEquals(testList, testMovieList.getAllMovies());
    }

    @Test
    public void testAddMultipleMovies() {
        testMovieList.addMovie(testMovie1);
        testMovieList.addMovie(testMovie2);
        testMovieList.addMovie(testMovie3);
        List<Movie> testList = new ArrayList<>();
        testList.add(testMovie1);
        testList.add(testMovie2);
        testList.add(testMovie3);
        assertEquals(testList, testMovieList.getAllMovies());
    }

    @Test
    public void testRemoveOneMovie() {
        testMovieList.addMovie(testMovie1);
        testMovieList.addMovie(testMovie2);
        testMovieList.removeMovie("Big Fish");
        List<Movie> testList = new ArrayList<>();
        testList.add(testMovie2);
        assertEquals(testList, testMovieList.getAllMovies());
    }

    @Test
    public void testMarkWatched() {
        testMovieList.addMovie(testMovie1);
        testMovieList.addMovie(testMovie2);
        testMovieList.addMovie(testMovie3);
        testMovieList.markWatchedMovie("Her");
        List<Movie> testList = new ArrayList<>();
        testList.add(testMovie3);
        assertEquals(testList, testMovieList.getWatchedMovies());
    }

    @Test
    public void testMarkUnwatched() {
        testMovieList.addMovie(testMovie1);
        testMovieList.addMovie(testMovie2);
        testMovieList.markUnwatchedMovie("Big Fish");
        List<Movie> testList = new ArrayList<>();
        testList.add(testMovie1);
        assertEquals(testList, testMovieList.getUnwatchedMovies());
    }

    @Test
    public void testGetAllMovieNames() {
        testMovieList.addMovie(testMovie3);
        testMovieList.addMovie(testMovie1);
        List<String> names = new ArrayList<>();
        names.add("Her");
        names.add("Big Fish");
        assertEquals(names, testMovieList.getAllMovieNames());
    }

    @Test
    public void testGetAllWatchedNames() {
        testMovieList.addMovie(testMovie3);
        testMovieList.addMovie(testMovie1);
        testMovieList.markWatchedMovie("Her");
        List<String> names = new ArrayList<>();
        names.add("Her");
        assertEquals(names, testMovieList.getWatchedMovieNames());
    }

    @Test
    public void testGetAllUnwatchedNames() {
        testMovieList.addMovie(testMovie3);
        testMovieList.addMovie(testMovie1);
        testMovieList.markUnwatchedMovie("Her");
        List<String> names = new ArrayList<>();
        names.add("Her");
        assertEquals(names, testMovieList.getUnwatchedMovieNames());
    }
}
