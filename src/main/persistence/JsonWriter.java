package persistence;

import model.MovieList;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Represents a writer that writes JSON representation of movielist to file
public class JsonWriter {
    public static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // EFFECTS: opens writer
    //          throws FileNotFoundException if destination file cannot be opened
    // MODIFIES: this
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // EFFECTS: writes JSON representation of movielist to file
    // MODIFIES: this
    public void write(MovieList movieList) {
        JSONObject json = movieList.toJson();
        saveToFile(json.toString(TAB));
    }

    // EFFECTS: closes writer
    // MODIFIES: this
    public void close() {
        writer.close();
    }

    // EFFECTS: writes string to file
    // MODIFIES: this
    private void saveToFile(String json) {
        writer.print(json);
    }
}
