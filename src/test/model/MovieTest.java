package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MovieTest {
    private Movie testMovie;

    @BeforeEach
    public void setup() {
        testMovie = new Movie("Big Fish", "Fantasy", 9);
    }

    @Test
    public void testGetName() {
        assertEquals("Big Fish", testMovie.getName());
    }

    @Test
    public void testGetGenre() {
        assertEquals("Fantasy", testMovie.getGenre());
    }

    @Test
    public void testGetRating() {
        assertEquals(9, testMovie.getRating());
    }
}