package ui;

import java.io.FileNotFoundException;

public class ConsoleUI {
    public static void main(String[] args) {
        try {
            new MovieWatchlist();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
