package mancala;

import java.io.Serializable;

public class KalahRules extends GameRules implements Serializable{

    final private MancalaDataStructure gameBoard;
    final private static int ONE = 1;
    final private static int THIRTEEN = 13;
    private int placeHolder;
    private int whichStore;
    //private int currentPlayer = 1; // Player number (1 or 2)

    private static final long serialVersionUID = 9136350261806655110L;

    public KalahRules() {
        super();
        gameBoard = getDataStructure();
    }

    /**
     * Perform a move and return the number of stones added to the player's store.
     *
     * @param startPit  The starting pit for the move.
     * @param playerNum The player making the move.
     * @return The number of stones added to the player's store.
     * @throws InvalidMoveException If the move is invalid.
     */
    @Override
    public int moveStones(final int startPit, final int playerNum) throws InvalidMoveException {
        // check exception for invalid move
        if (startPit > 12 || startPit < 1){
            throw new InvalidMoveException("Your move was out of bounds. This is an invalid move!");
        } else if (startPit >= 1 && startPit <= 6 && playerNum == 2) {
            throw new InvalidMoveException("You do not have access to this side of the board!");
        } else if (startPit >= 7 && startPit <= 12 && playerNum == 1) {
            throw new InvalidMoveException("You do not have access to this side of the board!");
        } else if (startPit >= 1 && startPit <= 6 && playerNum == 2) {
            throw new InvalidMoveException("You do not have access to this side of the board!");
        } else if (startPit >= 7 && startPit <= 12 && playerNum == 1) {
            throw new InvalidMoveException("You do not have access to this side of the board!");
        }
        
        final int beforeDistribute = gameBoard.getStoreCount(playerNum);
        distributeStones(startPit);
        final int afterDistribute = gameBoard.getStoreCount(playerNum);
        
        return afterDistribute-beforeDistribute;
    }
    
    @Override
    public int distributeStones(final int startingPoint){
        if (startingPoint >= 1 && startingPoint <= 6) {
            whichStore = 1;
        } else if(startingPoint >= 7 && startingPoint <= 12) {
            whichStore = 2;
        }
        int distributing;
        placeHolder = startingPoint;
        distributing = gameBoard.removeStones(startingPoint);
        gameBoard.setIterator(startingPoint, whichStore, false);
        Countable currentSpot;
        for (int i = 0; i < distributing; i++){
            currentSpot = gameBoard.next();
            currentSpot.addStone();
            //System.out.println("Iterator in loop = " + gameBoard.getIterator());
            //System.out.println("Pit #" + placeHolder + " with " + getNumStones(placeHolder) + "stones");
            placeHolder++;
            if (placeHolder >= THIRTEEN) {
                placeHolder = 1;
            }
        }
        //System.out.println("Ending pit #" + placeHolder);
        final int stoppingPoint = gameBoard.getIterator()+1; // stopping point will just be index
        if (stoppingPoint >= 1 && stoppingPoint <= 6 && whichStore == 1 && getNumStones(stoppingPoint) == ONE){ // on ending pit, call capture stones
            distributing += captureStones(stoppingPoint);   
        } else if (stoppingPoint >= 8 && stoppingPoint <= 13 && whichStore == 2 && getNumStones(stoppingPoint-1) == ONE){
            distributing += captureStones(stoppingPoint-1);
        }
        return distributing; // returns total number distributed
    }

    @Override
    public int captureStones(final int stoppingPoint) {
        final int min = 1;
        final int max = 12;
        final int correspondingNum = max - stoppingPoint + min;
        final int captured = gameBoard.removeStones(correspondingNum) + gameBoard.removeStones(stoppingPoint); // put stones in correct store
        if (stoppingPoint >= 1 && stoppingPoint <=6){
            gameBoard.addToStore(1, captured);
        } else if (stoppingPoint >= 7 && stoppingPoint <=12){
            gameBoard.addToStore(2, captured);
        }
        return captured; // returns all captured stones
    }
    
}
