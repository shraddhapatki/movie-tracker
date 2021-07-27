package ui;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieWatchlist {
    private MovieList movieList;
    private Scanner input;

    public MovieWatchlist() {
        runMovieWatchlist();
    }

    private void runMovieWatchlist() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("See you later!");
    }

    private void processCommand(String command) {
        if (command.equals("1")) {
            addMovie();
        } else if (command.equals("2")) {
            deleteMovie();
        } else if (command.equals("3")) {
            showAll();
        } else if (command.equals("4")) {
            markStatus();
        } else {
            System.out.println("Please enter an appropriate selection!");
        }
    }

    private void init() {
        movieList = new MovieList();
        input = new Scanner(System.in);
    }

    private void displayMenu() {
        System.out.println("\nWhat would you like to do today?");
        System.out.println("\t1 -> Add a movie");
        System.out.println("\t2 -> Delete a movie");
        System.out.println("\t3 -> See movie entries");
        System.out.println("\t4 -> Mark as watched or unwatched");
        System.out.println("\t5 -> Quit");
    }

    private void addMovie() {
        System.out.println("\nName?");
        String movieName = input.next();
        System.out.println("\nGenre?");
        String movieGenre = input.next();
        System.out.println("\nRating?");
        int movieRating = input.nextInt();
        Movie movie = new Movie(movieName, movieGenre, movieRating);
        movieList.addMovie(movie);
        System.out.println("\n New movie has been added to your list");
    }

    private void deleteMovie() {
        System.out.println("\nName?");
        String movieName = input.next();
        movieList.removeMovie(movieName);
        System.out.println("\nMovie has been removed from your list");
    }

    private void showAll() {
        System.out.println("\nChoose 1 for all movies, 2 for watched movies, 3 for unwatched movies");
        String choice = input.next();
        if (choice.equals("1")) {
            System.out.println(movieList.getAllMovieNames());
        } else if (choice.equals("2")) {
            System.out.println(movieList.getWatchedMovieNames());
        } else if (choice.equals("3")) {
            System.out.println(movieList.getUnwatchedMovieNames());
        }
    }

    private void markStatus() {
        System.out.println("\nWhich movie's status do you want to change?");
        String movieName = input.next();
        System.out.println("\nEnter 1 for watched or 2 for unwatched");
        String status = input.next();
        if (status == "1") {
            movieList.markWatchedMovie(movieName);
        } else if (status == "2") {
            movieList.markUnwatchedMovie(movieName);
        }
        System.out.println("\nThe changes have been made");
    }
}
