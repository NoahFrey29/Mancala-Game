package mancala;

public class PitNotFoundException extends Exception {
    public PitNotFoundException() {
        super("Pit does not exist!");
    }

    public PitNotFoundException(final String message) {
        super(message);
    }
}