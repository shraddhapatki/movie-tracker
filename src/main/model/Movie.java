package model;

import org.json.JSONObject;
import persistence.Writable;

public class Movie implements Writable {
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

    // EFFECTS: returns name
    public String getName() {
        return name;
    }

    // EFFECTS: returns genre
    public String getGenre() {
        return genre;
    }

    // EFFECTS: returns rating
    public int getRating() {
        return rating;
    }

    // EFFECTS: returns movie with its name, genre and rating
    public String getMovie() {
        return name + ": " + genre + ", " + rating;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("genre", genre);
        json.put("rating", rating);
        return json;
    }
}