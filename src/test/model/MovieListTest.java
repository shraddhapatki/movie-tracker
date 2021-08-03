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
        testMovieList = new MovieList("test list");
        testMovie1 = new Movie("Big Fish", "Fantasy", 9);
        testMovie2 = new Movie("Beautiful Boy", "Drama", 7);
        testMovie3 = new Movie("Her", "Sci-Fi", 8);
    }

    @Test
    public void testAddOneMovie() {
        testMovieList.addAll(testMovie1);
        List<Movie> testList = new ArrayList<>();
        testList.add(testMovie1);
        assertEquals(testList, testMovieList.getAllMovies());
    }

    @Test
    public void testAddMultipleMovies() {
        testMovieList.addAll(testMovie1);
        testMovieList.addAll(testMovie2);
        testMovieList.addAll(testMovie3);
        List<Movie> testList = new ArrayList<>();
        testList.add(testMovie1);
        testList.add(testMovie2);
        testList.add(testMovie3);
        assertEquals(testList, testMovieList.getAllMovies());
    }

    @Test
    public void testRemoveMovie1() {
        testMovieList.addAll(testMovie1);
        testMovieList.addAll(testMovie2);
        testMovieList.markWatchedMovie("Big Fish");
        testMovieList.removeMovie("Big Fish");
        List<Movie> testList = new ArrayList<>();
        assertEquals(testList, testMovieList.getWatchedMovies());
        testList.add(testMovie2);
        assertEquals(testList, testMovieList.getAllMovies());
    }

    @Test
    public void testRemoveMovie2() {
        testMovieList.addAll(testMovie1);
        testMovieList.addAll(testMovie2);
        testMovieList.markUnwatchedMovie("Big Fish");
        testMovieList.removeMovie("Big Fish");
        List<Movie> testList = new ArrayList<>();
        assertEquals(testList, testMovieList.getUnwatchedMovieNames());
    }

    @Test
    public void testRemoveMovie3() {
        testMovieList.addAll(testMovie1);
        testMovieList.addAll(testMovie2);
        testMovieList.removeMovie("Big Fish");
        List<Movie> testList = new ArrayList<>();
        assertEquals(testList, testMovieList.getUnwatchedMovies());
        assertEquals(testList, testMovieList.getWatchedMovies());
        testList.add(testMovie2);
        assertEquals(testList, testMovieList.getAllMovies());
    }

    @Test
    public void testMarkWatched() {
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
    }

    @Test
    public void testMarkWatched2() {
        testMovieList.addAll(testMovie1);
        testMovieList.markWatchedMovie("Big Fish");
        List<Movie> testList = new ArrayList<>();
        testList.add(testMovie1);
        assertEquals(testList, testMovieList.getWatchedMovies());
    }

    @Test
    public void testMarkUnwatched() {
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
    }

    @Test
    public void testMarkUnwatched2() {
        testMovieList.addAll(testMovie1);
        testMovieList.markUnwatchedMovie("Big Fish");
        List<Movie> testList = new ArrayList<>();
        testList.add(testMovie1);
        assertEquals(testList, testMovieList.getUnwatchedMovies());
    }

    @Test
    public void testGetAllMovieNames() {
        testMovieList.addAll(testMovie3);
        testMovieList.addAll(testMovie1);
        List<String> names = new ArrayList<>();
        names.add("Her");
        names.add("Big Fish");
        assertEquals(names, testMovieList.getAllMovieNames());
    }

    @Test
    public void testGetAllWatchedNames() {
        testMovieList.addAll(testMovie3);
        testMovieList.addAll(testMovie1);
        testMovieList.markWatchedMovie("Her");
        List<String> names = new ArrayList<>();
        names.add("Her");
        assertEquals(names, testMovieList.getWatchedMovieNames());
    }

    @Test
    public void testGetAllUnwatchedNames() {
        testMovieList.addAll(testMovie3);
        testMovieList.addAll(testMovie1);
        testMovieList.markUnwatchedMovie("Her");
        List<String> names = new ArrayList<>();
        names.add("Her");
        assertEquals(names, testMovieList.getUnwatchedMovieNames());
    }
}