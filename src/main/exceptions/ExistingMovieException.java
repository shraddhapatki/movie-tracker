package exceptions;

public class ExistingMovieException extends Exception {

    public ExistingMovieException(String message) {
        super(message);
    }
}
