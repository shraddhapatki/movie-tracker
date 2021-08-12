package persistence;

import exceptions.ExistingMovieException;
import model.*;

import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// CITATION: received advice and assistance from TA during Nuts' office hours

// Citation: Structure and methods are implemented from JsonSerializationDemo. Link below:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo


// Represents a reader that reads movie list from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads movielist from file and returns it
    // throws IOException if an error occurs while reading data
    public MovieList read() throws IOException, ExistingMovieException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMovieList(jsonObject);
    }

    // EFFECTS: reads file as a string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses movielist from JSON object and returns it
    private MovieList parseMovieList(JSONObject jsonObject) throws ExistingMovieException {
        String name = jsonObject.getString("name");
        MovieList movieList = new MovieList(name);
        addMovies(movieList, jsonObject);
        addUnwatched(movieList, jsonObject);
        addWatched(movieList, jsonObject);
        return movieList;
    }

    // EFFECTS: parses all movies list from JSON object and adds them to movielist
    // MODIFIES: movieList
    public void addMovies(MovieList movieList, JSONObject jsonObject) throws ExistingMovieException {
        JSONArray jsonArray = jsonObject.getJSONArray("movies");
        for (Object json : jsonArray) {
            JSONObject nextMovie = (JSONObject) json;
            addMovie(movieList, nextMovie, "all");
        }
    }

    // EFFECTS: parses unwatched movies list from JSON object and adds them to movielist
    // MODIFIES: movieList
    private void addUnwatched(MovieList movieList, JSONObject jsonObject) throws ExistingMovieException {
        JSONArray jsonArray = jsonObject.getJSONArray("unwatched movies");
        for (Object json : jsonArray) {
            JSONObject nextMovie = (JSONObject) json;
            addMovie(movieList, nextMovie, "unwatched");
        }
    }

    // EFFECTS: parses watched movies list from JSON object and adds them to movielist
    // MODIFIES: movieList
    private void addWatched(MovieList movieList, JSONObject jsonObject) throws ExistingMovieException {
        JSONArray jsonArray = jsonObject.getJSONArray("watched movies");
        for (Object json : jsonArray) {
            JSONObject nextMovie = (JSONObject) json;
            addMovie(movieList, nextMovie, "watched");
        }
    }

    // EFFECTS: parses all/watched/unwatched movie from JSON object and adds them to movielist
    // MODIFIES: movieList
    public void addMovie(MovieList movieList, JSONObject jsonObject, String status) throws ExistingMovieException {
        String name = jsonObject.getString("name");
        String genre = jsonObject.getString("genre");
        int rating = jsonObject.getInt("rating");
        Movie movie = new Movie(name, genre, rating);
        if (status.equals("all")) {
            movieList.addAll(movie);
        } else if (status.equals("watched")) {
            movieList.addWatched(movie);
        } else {
            movieList.addUnwatched(movie);
        }
    }
}
