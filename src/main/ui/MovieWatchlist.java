package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Citation: UI Functionality and methods are implemented from Teller App. Link below:
// https://github.students.cs.ubc.ca/CPSC210/TellerApp

public class MovieWatchlist {
    private static final String JSON_STORE = "./data/movielist.json";
    private MovieList movieList;
    private Scanner input;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    // EFFECTS: runs the movie watchlist application
    public MovieWatchlist() throws FileNotFoundException {
        input = new Scanner(System.in);
        movieList = new MovieList("Shraddha's Movie List");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runMovieWatchlist();
    }

    // MODIFIES: this
    private void runMovieWatchlist() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("7")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("See you later!");
    }

    // EFFECTS: processes user input
    private void processCommand(String command) {
        if (command.equals("1")) {
            addMovie();
        } else if (command.equals("2")) {
            deleteMovie();
        } else if (command.equals("3")) {
            showAll();
        } else if (command.equals("4")) {
            markStatus();
        } else if (command.equals("5")) {
            saveMovieList();
        } else if (command.equals("6")) {
            loadMovieList();
        } else {
            System.out.println("Please enter an appropriate selection!");
        }
    }

    // EFFECTS: displays menu to user
    private void displayMenu() {
        System.out.println("\nWhat would you like to do today?");
        System.out.println("\t1 -> Add a movie");
        System.out.println("\t2 -> Delete a movie");
        System.out.println("\t3 -> See movie entries");
        System.out.println("\t4 -> Mark as watched or unwatched");
        System.out.println("\t5 -> Save movie list to file");
        System.out.println("\t6 -> Load movie list from file");
        System.out.println("\t7 -> Quit");
    }

    // EFFECTS: adds a movies
    private void addMovie() {
        System.out.println("\nName?");
        input.nextLine();
        String movieName = input.nextLine();
        System.out.println("\nGenre?");
        String movieGenre = input.next();
        System.out.println("\nRating?");
        int movieRating = input.nextInt();
        Movie movie = new Movie(movieName, movieGenre, movieRating);
        movieList.addAll(movie);
        System.out.println("\n New movie has been added to your list");
    }

    private void deleteMovie() {
        System.out.println("\nName?");
        input.nextLine();
        String movieName = input.nextLine();
        movieList.removeMovie(movieName);
        System.out.println("\nMovie has been removed from your list");
    }

    private void showAll() {
        System.out.println("\nChoose 1 for all movies, 2 for watched movies, 3 for unwatched movies");
        String choice = input.next();
        if (choice.equals("1")) {
            List<Movie> movies = movieList.getAllMovies();
            for (Movie m : movies) {
                System.out.println(m.getMovie());
            }
        } else if (choice.equals("2")) {
            List<Movie> movies = movieList.getWatchedMovies();
            for (Movie m : movies) {
                System.out.println(m.getMovie());
            }
        } else if (choice.equals("3")) {
            List<Movie> movies = movieList.getUnwatchedMovies();
            for (Movie m : movies) {
                System.out.println(m.getMovie());
            }
        }
    }

    private void markStatus() {
        System.out.println("\nWhich movie's status do you want to change?");
        input.nextLine();
        String movieName = input.nextLine();
        System.out.println("\nEnter 1 for watched or 2 for unwatched");
        String status = input.next();
        if (status.equals("1")) {
            movieList.markWatchedMovie(movieName);
        } else if (status.equals("2")) {
            movieList.markUnwatchedMovie(movieName);
        }
        System.out.println("\nThe changes have been made");
    }

    private void saveMovieList() {
        try {
            jsonWriter.open();
            jsonWriter.write(movieList);
            jsonWriter.close();
            System.out.println("Saved your movie list to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save to " + JSON_STORE);
        }
    }

    private void loadMovieList() {
        try {
            movieList = jsonReader.read();
            System.out.println("Loaded your movie list from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to load from " + JSON_STORE);
        }
    }
}