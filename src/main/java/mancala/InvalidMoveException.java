package mancala;

public class InvalidMoveException extends Exception {
    public InvalidMoveException() {
        super("This move does not exist!");
    }

    public InvalidMoveException(final String message) {
        super(message);
    }
}