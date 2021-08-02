package persistence;

import model.*;

import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads movie list from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS:
    public JsonReader(String source) {
        this.source = source;
    }

    public MovieList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMovieList(jsonObject);
    }

    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    private MovieList parseMovieList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        MovieList movieList = new MovieList(name);
        addMovies(movieList, jsonObject);
        addUnwatched(movieList, jsonObject);
        addWatched(movieList, jsonObject);
        return movieList;
    }

    private void addMovies(MovieList movieList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("movies");
        for (Object json : jsonArray) {
            JSONObject nextMovie = (JSONObject) json;
            addMovie(movieList, nextMovie, "all");
        }
    }

    private void addUnwatched(MovieList movieList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("unwatched movies");
        for (Object json : jsonArray) {
            JSONObject nextMovie = (JSONObject) json;
            addMovie(movieList, nextMovie, "unwatched");
        }
    }

    private void addWatched(MovieList movieList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("watched movies");
        for (Object json : jsonArray) {
            JSONObject nextMovie = (JSONObject) json;
            addMovie(movieList, nextMovie, "watched");
        }
    }

    private void addMovie(MovieList movieList, JSONObject jsonObject, String status) {
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
