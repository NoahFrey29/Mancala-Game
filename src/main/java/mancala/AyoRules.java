package mancala;

import java.io.Serializable;

public class AyoRules extends GameRules implements Serializable {
  
    private MancalaDataStructure gameBoard;
    private int currentPlayer = 1; // Player number (1 or 2)

    public int moveStones(final int startPit, final int playerNum) throws InvalidMoveException {
        return 0;
    }
    public int distributeStones(int startingPoint){
        return 0;
    }
    public int captureStones(final int stoppingPoint) {
        return 0;
    }
    

}
