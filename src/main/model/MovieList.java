package model;

import java.util.ArrayList;
import java.util.List;

public class MovieList {
    private List<Movie> unwatchedMovies;
    private List<Movie> watchedMovies;
    private List<Movie> allMovies;

    // EFFECTS: Constructs a new all movies list, unwatched movies list and watched movies list
    public MovieList() {
        unwatchedMovies = new ArrayList<>();
        watchedMovies = new ArrayList<>();
        allMovies = new ArrayList<>();
    }

    // EFFECTS: Adds movie to all movies list
    public void addMovie(Movie movie) {
        allMovies.add(movie);
    }

    // EFFECTS: Removes movie from all movies list using movie name
    public void removeMovie(String movieName) {
        allMovies.removeIf(i -> movieName.equals(i.getName()));
    }

    // EFFECTS: Adds movie to watched movies list using movie name
    public void markWatchedMovie(String movieName) {
        for (Movie i : allMovies) {
            if (movieName.equals(i.getName())) {
                watchedMovies.add(i);
            }
        }
    }

    // EFFECTS: Adds movie to unwatched movies list using movie name
    public void markUnwatchedMovie(String movieName) {
        for (Movie i : allMovies) {
            if (movieName.equals(i.getName())) {
                unwatchedMovies.add(i);
            }
        }
    }

    // EFFECTS: returns list of all movies
    public List<Movie> getAllMovies() {
        return allMovies;
    }

    // EFFECTS: returns list of all watched movies
    public List<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    // EFFECTS: returns list of unwatched movies
    public List<Movie> getUnwatchedMovies() {
        return unwatchedMovies;
    }

    // EFFECTS: returns list of all movie names
    public List<String> getAllMovieNames() {
        List<String> names = new ArrayList<>();
        for (Movie i : allMovies) {
            String name = i.getName();
            names.add(name);
        }
        return names;
    }

    // EFFECTS: returns list of all watched movie names
    public List<String> getWatchedMovieNames() {
        List<String> names = new ArrayList<>();
        for (Movie i : watchedMovies) {
            String name = i.getName();
            names.add(name);
        }
        return names;
    }

    // EFFECTS: returns list of all unwatched movie names
    public List<String> getUnwatchedMovieNames() {
        List<String> names = new ArrayList<>();
        for (Movie i : unwatchedMovies) {
            String name = i.getName();
            names.add(name);
        }
        return names;
    }
}