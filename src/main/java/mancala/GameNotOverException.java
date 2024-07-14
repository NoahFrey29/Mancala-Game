package mancala;

import java.io.Serializable;

public class GameNotOverException extends Exception implements Serializable {

    private static final long serialVersionUID = -1894883535467494497L;

    public GameNotOverException() {
        super("The game is not over!");
    }

    public GameNotOverException(final String message) {
        super(message);
    }
}