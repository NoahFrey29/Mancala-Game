package mancala;

public class KalahRules extends GameRules {

private MancalaDataStructure gameBoard;
    private int currentPlayer = 1; // Player number (1 or 2)

    /**
     * Perform a move and return the number of stones added to the player's store.
     *
     * @param startPit  The starting pit for the move.
     * @param playerNum The player making the move.
     * @return The number of stones added to the player's store.
     * @throws InvalidMoveException If the move is invalid.
     */
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
        int whichStore = 0;
        if (startingPoint >= 0 && startingPoint <= 5) {
            whichStore = 1;
        } else if(startingPoint >= 7 && startingPoint <= 12) {
            whichStore = 2;
        }
        int distributing;
        int placeHolder;
        //distributing = gameBoard.removeStones(startingPoint);
        int index = startingPoint-1; //index controls what pit you are on
        
        distributing = gameBoard.removeStones(startingPoint);
        gameBoard.setIterator(startingPoint, whichStore, false);
        Countable currentSpot;
        for (int i = 0; i < distributing; i++){
            currentSpot = gameBoard.next();
            currentSpot.addStone();
        }
/* 
        final int stoppingPoint = index+1; // stopping point will just be index
        if ((stoppingPoint >= 1 && stoppingPoint <= 6) && whichStore == 1){ // on ending pit, call capture stones
            if (pits.get(stoppingPoint-1).getStoneCount() == ONE) {
                placeHolder += captureStones(stoppingPoint);
            }           
        } else if ((stoppingPoint >= 7 && stoppingPoint <= 12) && whichStore == 2){
            if (pits.get(stoppingPoint-1).getStoneCount() == ONE) {
                placeHolder += captureStones(stoppingPoint);
            }   
        }
        */
        return distributing; // returns total number distributed
    }
    public int captureStones(final int stoppingPoint) {
        final int min = 1;
        final int max = 13;
        final int correspondingNum = max - stoppingPoint + min;
        final int captured = gameBoard.removeStones(correspondingNum-1); // put stones in correct store
        if (stoppingPoint >= 1 && stoppingPoint <=6){
            gameBoard.addToStore(1, captured);
        } else if (stoppingPoint >= 7 && stoppingPoint <=12){
            gameBoard.addToStore(2, captured);
        }
        return captured; // returns all captured stones
    }
    
}
