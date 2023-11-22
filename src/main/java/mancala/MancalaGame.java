package mancala;

import java.io.Serializable;
import java.util.ArrayList;

public class MancalaGame implements Serializable {

    //private MancalaGame game;
    private final ArrayList<Player> players;
    private Player currentPlayer;
    private Board gameBoard;

    public MancalaGame(){
        players = new ArrayList<>(2);
        gameBoard = new Board();
    }

    /* 
    public void test() throws PitNotFoundException {
        gameBoard.getPits().get(7).addStone();
        gameBoard.distributeStones(8);
        System.out.println("Initial board:" + gameBoard.toString());

        //distribute = gameBoard.distributeStones(2);
        //System.out.println("Initial board:" + gameBoard.toString());
        //distribute = gameBoard.distributeStones(6);
        //System.out.println("Initial board:" + gameBoard.toString());
        try {
            gameBoard.distributeStones(12);
        System.out.println("Initial board:" + gameBoard.toString());
        } catch(IndexOutOfBoundsException err){
            System.out.println(err.getMessage());
        }
        
    }*/

    public void setPlayers(final Player onePlayer, final Player twoPlayer) {
        gameBoard.registerPlayers(onePlayer, twoPlayer);
        players.add(onePlayer);
        players.add(twoPlayer);
        currentPlayer = onePlayer; // set current player to the first one always
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }
    public Player getCurrentPlayer(){
        return currentPlayer;
    }
    public void setCurrentPlayer(final Player player) {
        currentPlayer = player;
    }

    void setBoard(final Board theBoard){
        gameBoard = theBoard;
    }
    public Board getBoard(){
        return gameBoard;
    }

    public int getNumStones(final int pitNum) throws PitNotFoundException {
        if (pitNum > 12 || pitNum < 1){
            throw new PitNotFoundException("Pit not found!");
        }
        return gameBoard.getPits().get(pitNum).getStoneCount();
    }
    public int move(final int startPit) throws InvalidMoveException{
        if (startPit > 12 || startPit < 1){
            throw new InvalidMoveException("This is an invalid move!");
        } /*else if ((startPit >= 1 && startPit <= 6) && player.equals(stores.get(1).getOwner())) {
            throw new InvalidMoveException("You do not have access to this side of the board!");
        } else if ((startPit >= 7 && startPit <= 12) && player.equals(stores.get(0).getOwner())) {
            throw new InvalidMoveException("You do not have access to this side of the board!");
        }*/
        int beforeMove = 0;
        int afterMove = 0;
        if (startPit >= 1 && startPit <= 6) {
            //setCurrentPlayer(players.get(0));
            beforeMove = currentPlayer.getStoreCount();
            afterMove = gameBoard.moveStones(startPit, currentPlayer);
        } else if (startPit >= 7 && startPit <= 12) {
            //setCurrentPlayer(players.get(1));
            beforeMove = currentPlayer.getStoreCount();
            afterMove = gameBoard.moveStones(startPit, currentPlayer);
        }
        
        // while loop that checks for free turns

        // change current player at the end of the successful turn
        return afterMove-beforeMove;
    }
    public int getStoreCount(final Player player) throws NoSuchPlayerException{
        if (player == null) {
            throw new NoSuchPlayerException();
        }   
        return player.getStoreCount();
    }

    public Player getWinner() throws GameNotOverException{
        
        if (!isGameOver()) {
            throw new GameNotOverException();
        }
        if (players.get(0).getStoreCount() > players.get(1).getStoreCount()) {
            return players.get(0);
        } else if (players.get(0).getStoreCount() < players.get(1).getStoreCount()) {
            return players.get(1);
        } else {//(tie)
            return null;
        }
    }
    public boolean isGameOver() {
        try {
            if (gameBoard.isSideEmpty(1)) {
                return true;
            } else if (gameBoard.isSideEmpty(7)){
                return true;
            }
        } catch (PitNotFoundException err){
            System.out.println(err.getMessage());
        } 
        return false;
    }
    public void startNewGame() {
        gameBoard.resetBoard(); // set board and initialize? Reset?
    }
    
    @Override
    public String toString() {
        final StringBuilder gameString = new StringBuilder();
        gameString.append("Current Player: ").append(currentPlayer.getName()).append("\n");
        gameString.append("Player 1: ").append(players.get(0).getName()).append("\n");
        gameString.append("Player 2: ").append(players.get(1).getName()).append("\n");
        gameString.append(gameBoard.toString());
        return gameString.toString();
    }
    

}