package ui;

import model.Movie;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new MovieWatchlist();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
