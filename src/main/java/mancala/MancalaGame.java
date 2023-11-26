package mancala;

import java.io.Serializable;
import java.util.ArrayList;

public class MancalaGame implements Serializable {

    //private MancalaGame game;
    private final ArrayList<Player> players;
    private Player currentPlayer;
    private GameRules gameRules;

    private static final long serialVersionUID = -2050628086373019704L;

    public MancalaGame(){
        players = new ArrayList<>(2);
        gameRules = new AyoRules();
        //gameRules.getDataStructure().setUpPits(); // tentative call
    }

    /* 
    public void test() throws PitNotFoundException {
        gameRules.getPits().get(7).addStone();
        gameRules.distributeStones(8);
        System.out.println("Initial board:" + gameRules.toString());

        //distribute = gameRules.distributeStones(2);
        //System.out.println("Initial board:" + gameRules.toString());
        //distribute = gameRules.distributeStones(6);
        //System.out.println("Initial board:" + gameRules.toString());
        try {
            gameRules.distributeStones(12);
        System.out.println("Initial board:" + gameRules.toString());
        } catch(IndexOutOfBoundsException err){
            System.out.println(err.getMessage());
        }
        
    }*/

    public void setPlayers(final Player onePlayer, final Player twoPlayer) {
        gameRules.registerPlayers(onePlayer, twoPlayer);
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
    
    void setBoard(final GameRules theBoard){
        gameRules = theBoard;
    }
    public GameRules getBoard(){
        return gameRules;
    }
    
    public int getNumStones(final int pitNum) {
        return gameRules.getNumStones(pitNum);
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
        //gameRules.getDataStructure().addStones(12, 2);
        //gameRules.getDataStructure().removeStones(1);

        if (startPit >= 1 && startPit <= 6) {
            //setCurrentPlayer(players.get(0));
            beforeMove = gameRules.getDataStructure().getStoreCount(1);
            afterMove = gameRules.moveStones(startPit, 1);
        } else if (startPit >= 7 && startPit <= 12) {
            //setCurrentPlayer(players.get(1));
            beforeMove = gameRules.getDataStructure().getStoreCount(2);
            afterMove = gameRules.moveStones(startPit, 2);
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
        
        if (gameRules.isSideEmpty(1)) {
            return true;
        } else if (gameRules.isSideEmpty(7)){
            return true;
        } 
        return false;
    }
    public void startNewGame() {
        gameRules.resetBoard(); // set board and initialize? Reset?
    }
    
    @Override
    public String toString() {
        final StringBuilder gameString = new StringBuilder();
        gameString.append("Current Player: ").append(currentPlayer.getName()).append("\n");
        gameString.append("Player 1: ").append(players.get(0).getName()).append("\n");
        gameString.append("Player 2: ").append(players.get(1).getName()).append("\n");
        gameString.append(gameRules.toString());
        return gameString.toString();
    }
    

}