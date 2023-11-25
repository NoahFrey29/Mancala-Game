package mancala;

import java.io.Serializable;

public class AyoRules extends GameRules implements Serializable {
  
    final private MancalaDataStructure gameBoard;
    final private static int THIRTEEN = 13;
    //private int currentPlayer = 1; // Player number (1 or 2)

    private static final long serialVersionUID = -6060757419404324290L;

    public AyoRules() {
        super();
        gameBoard = getDataStructure();
    }

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
        int whichStore = 0;
        System.out.println("Starting point:" + startingPoint);
        if (startingPoint >= 1 && startingPoint <= 6) {
            //startingPoint--; // doing what pitPos is supposed to do but its private :/
            whichStore = 1;
        } else if(startingPoint >= 7 && startingPoint <= 12) {
            whichStore = 2;
        }
        int distributing = 0;
        int looper = 0;
        int placeHolder = startingPoint;
        System.out.println("whichStore = " + whichStore);
        
        gameBoard.setIterator(startingPoint, whichStore, true);
        Countable currentSpot;
        do {
            looper = gameBoard.removeStones(placeHolder);
            System.out.println("Removing stones at pit " + placeHolder + "...");
            distributing += looper;
            while (looper > 0){
                if (placeHolder == startingPoint) {
                    placeHolder++;
                    System.out.println("Skipping pit that was started on for placeholder...");
                    System.out.println("Placeholder:" + placeHolder);
                } else {

                    if (whichStore == 1 && placeHolder == 7) {
                    System.out.println("Going to add to store #1");
                    currentSpot = gameBoard.next();
                    currentSpot.addStone();
                    looper--;
                    //System.out.println("Pit #" + placeHolder + "with " + getNumStones(placeHolder) + "stones");
                } else if (whichStore == 2 && placeHolder == THIRTEEN) {
                    System.out.println("Going to add to store #2");
                    currentSpot = gameBoard.next();
                    currentSpot.addStone();
                    //System.out.println("Pit #" + placeHolder + "with " + getNumStones(placeHolder) + "stones");
                    if (placeHolder >= THIRTEEN) {
                        placeHolder = 1;
                    }
                    looper--;
                } 
                if (looper > 0) {
                    System.out.println("Going to add to a pit");
                    currentSpot = gameBoard.next();
                    currentSpot.addStone();
                    placeHolder++;
                    if (placeHolder >= THIRTEEN) {
                        placeHolder = 1;
                    }
                    System.out.println("Pit #" + placeHolder + "with " + getNumStones(placeHolder) + "stones");
                    looper--;
                }
                }
            }
            System.out.println("Ending pit #" + placeHolder);
            System.out.println("The current board looks like");
            
            System.out.println(toString());
        } while (getNumStones(placeHolder) != 0);

        System.out.println("Ending pit #" + placeHolder);
 
        final int stoppingPoint = gameBoard.getIterator()+1; // stopping point will just be index
        System.out.println("iteratorPos = " + stoppingPoint);
        if (stoppingPoint >= 1 && stoppingPoint <= 6 && whichStore == 1 && getNumStones(stoppingPoint) == 1){ // on ending pit, call capture stones
            distributing += captureStones(stoppingPoint);     
        } else if (stoppingPoint >= 7 && stoppingPoint <= 12 && whichStore == 2 && getNumStones(stoppingPoint) == 1){
            distributing += captureStones(stoppingPoint);  
        }
        return distributing; // returns total number distributed
    }

    @Override
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
