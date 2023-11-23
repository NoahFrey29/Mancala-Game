package mancala;

import java.io.Serializable;

public class AyoRules extends GameRules implements Serializable {
  
    private MancalaDataStructure gameBoard;
    private int currentPlayer = 1; // Player number (1 or 2)

    public int moveStones(final int startPit, final int playerNum) throws InvalidMoveException {
        // check exception for invalid move
        if (startPit > 12 || startPit < 1){
            throw new InvalidMoveException("Your move was out of bounds. This is an invalid move!");
        } else if ((startPit >= 1 && startPit <= 6) && playerNum == 2) {
            throw new InvalidMoveException("You do not have access to this side of the board!");
        } else if ((startPit >= 7 && startPit <= 12) && playerNum == 1) {
            throw new InvalidMoveException("You do not have access to this side of the board!");
        } else if ((startPit >= 1 && startPit <= 6) && playerNum == 2) {
            throw new InvalidMoveException("You do not have access to this side of the board!");
        } else if ((startPit >= 7 && startPit <= 12) && playerNum == 1) {
            throw new InvalidMoveException("You do not have access to this side of the board!");
        }
        
        final int beforeDistribute = gameBoard.getStoreCount(playerNum);
        distributeStones(startPit);
        final int afterDistribute = gameBoard.getStoreCount(playerNum);
        
        return afterDistribute-beforeDistribute;
    }
    public int distributeStones(int startingPoint){
        return 0;
    }
    public int captureStones(final int stoppingPoint) {
        final int min = 1;
        final int max = 12;
        final int correspondingNum = max - stoppingPoint + min;
        final int captured = gameBoard.removeStones(correspondingNum); // put stones in correct store
        if (stoppingPoint >= 1 && stoppingPoint <=6){
            gameBoard.addToStore(1, captured);
        } else if (stoppingPoint >= 7 && stoppingPoint <=12){
            gameBoard.addToStore(2, captured);
        }
        return captured; // returns all captured stones
    }
    

}
