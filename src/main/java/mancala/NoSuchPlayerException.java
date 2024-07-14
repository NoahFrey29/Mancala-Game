package mancala;

import java.io.Serializable;

public class NoSuchPlayerException extends Exception implements Serializable{

    private static final long serialVersionUID = 7791337417655979794L;

    public NoSuchPlayerException() {
        super("No such player exists!");
    }

    public NoSuchPlayerException(final String message) {
        super(message);
    }
}