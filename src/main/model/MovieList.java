package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// CITATION: received advice and assistance from TA during Nuts' office hours

public class MovieList implements Writable {
    private List<Movie> unwatchedMovies;
    private List<Movie> watchedMovies;
    private List<Movie> allMovies;
    private String listName;

    // EFFECTS: Constructs a new all movies list, unwatched movies list and watched movies list
    public MovieList(String name) {
        listName = name;
        unwatchedMovies = new ArrayList<>();
        watchedMovies = new ArrayList<>();
        allMovies = new ArrayList<>();
    }

    // EFFECTS: returns listName
    public String getName() {
        return listName;
    }

    // EFFECTS: adds movie to all movies list
    public void addAll(Movie movie) {
        allMovies.add(movie);
    }

    // EFFECTS: adds movie to unwatched movies list
    public void addUnwatched(Movie movie) {
        unwatchedMovies.add(movie);
    }

    // EFFECTS: adds movie to watched movies list
    public void addWatched(Movie movie) {
        watchedMovies.add(movie);
    }

    // EFFECTS: Removes movie from all movies list and unwatched or watched movies list using movie name
    public void removeMovie(String movieName) {
        for (Movie m : allMovies) {
            if (movieName.equals(m.getName())) {
                allMovies.remove(m);
                if (watchedMovies.contains(m)) {
                    watchedMovies.remove(m);
                } else {
                    unwatchedMovies.remove(m);
                }
            }
        }
    }

    // EFFECTS: Adds movie to watched movies list using movie name
    public void markWatchedMovie(String movieName) {
        for (Movie i : allMovies) {
            if (movieName.equals(i.getName())) {
                if (!watchedMovies.contains(i)) {
                    watchedMovies.add(i);
                    if (unwatchedMovies.contains(i)) {
                        unwatchedMovies.remove(i);
                    }
                }
            }
        }
    }

    // EFFECTS: Adds movie to unwatched movies list using movie name
    public void markUnwatchedMovie(String movieName) {
        for (Movie i : allMovies) {
            if (movieName.equals(i.getName())) {
                if (!unwatchedMovies.contains(i)) {
                    unwatchedMovies.add(i);
                    if (watchedMovies.contains(i)) {
                        watchedMovies.remove(i);
                    }
                }
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


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", listName);
        json.put("movies", allMoviesToJson());
        json.put("watched movies", watchedMoviesToJson());
        json.put("unwatched movies", unwatchedMoviesToJson());
        return json;
    }

    // EFFECTS: returns all movies as a JSON array
    private JSONArray allMoviesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Movie m : allMovies) {
            jsonArray.put(m.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns watched movies as a JSON array
    public JSONArray watchedMoviesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Movie m : watchedMovies) {
            jsonArray.put(m.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns unwatched movies as a JSON array
    public JSONArray unwatchedMoviesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Movie m : unwatchedMovies) {
            jsonArray.put(m.toJson());
        }
        return jsonArray;
    }
}