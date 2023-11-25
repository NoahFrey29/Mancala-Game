package mancala;

import java.io.Serializable;

public class InvalidMoveException extends Exception implements Serializable {

    private static final long serialVersionUID = 4053673262215290176L;

    public InvalidMoveException() {
        super("This move does not exist!");
    }

    public InvalidMoveException(final String message) {
        super(message);
    }
}