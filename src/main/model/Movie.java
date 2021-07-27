package model;

public class Movie {
    private String name;
    private String genre;
    private int rating;

    // EFFECTS: Constructs a Movie
    // REQUIRES: movieRating is an int, movieName and movieGenre are String
    public Movie(String movieName, String movieGenre, int movieRating) {
        name = movieName;
        genre = movieGenre;
        rating = movieRating;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public int getRating() {
        return rating;
    }
}