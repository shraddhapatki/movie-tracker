package model;

import java.util.ArrayList;
import java.util.List;

public class MovieList {
    private List<Movie> unwatchedMovies;
    private List<Movie> watchedMovies;
    private List<Movie> allMovies;


    public MovieList() {
        unwatchedMovies = new ArrayList<>();
        watchedMovies = new ArrayList<>();
        allMovies = new ArrayList<>();
    }

    public void addMovie(Movie movie) {
        allMovies.add(movie);
    }

    public void removeMovie(String movieName) {
        allMovies.removeIf(i -> movieName.equals(i.getName()));
    }

    public void markWatchedMovie(String movieName) {
        for (Movie i : allMovies) {
            if (movieName.equals(i.getName())) {
                watchedMovies.add(i);
            }
        }
    }

    public void markUnwatchedMovie(String movieName) {
        for (Movie i : allMovies) {
            if (movieName.equals(i.getName())) {
                unwatchedMovies.add(i);
            }
        }
    }

    public List<Movie> getAllMovies() {
        return allMovies;
    }

    public List<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public List<Movie> getUnwatchedMovies() {
        return unwatchedMovies;
    }

    public List<String> getAllMovieNames() {
        List<String> names = new ArrayList<>();
        for (Movie i : allMovies) {
            String name = i.getName();
            names.add(name);
        }
        return names;
    }

    public List<String> getWatchedMovieNames() {
        List<String> names = new ArrayList<>();
        for (Movie i : watchedMovies) {
            String name = i.getName();
            names.add(name);
        }
        return names;
    }

    public List<String> getUnwatchedMovieNames() {
        List<String> names = new ArrayList<>();
        for (Movie i : unwatchedMovies) {
            String name = i.getName();
            names.add(name);
        }
        return names;
    }
}
