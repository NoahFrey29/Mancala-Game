package mancala;

import java.util.ArrayList;
import java.io.Serializable;

public class Board implements Serializable{

    private ArrayList<Pit> pits;
    private ArrayList<Store> stores;
    private int freeMove = 0;
    private final static int TWELVE = 12;
    private final static int ONE = 1;

    public Board(){
        setUpPits();
        setUpStores();
        initializeBoard();
    }

    /* default access modifier */ void setUpPits(){
        pits = new ArrayList<>(12);
        for (int i = 0; i < 12; i++){
            pits.add(new Pit());
        }
    }
    public ArrayList<Pit> getPits(){
        return pits;
    }

    public ArrayList<Store> getStores() {
        return stores;
    }
    /* default access modifier */ void setUpStores(){
        stores = new ArrayList<>(2);
        for (int i = 0; i < 2; i++) {
            stores.add(new Store());
        }
    }

    public void initializeBoard(){
        for (int j = 0; j < 12; j++) {
            for (int i = 0; i < 4; i++){
                pits.get(j).addStone();
            }
        }
    }
    public void resetBoard(){
        for (final Pit fulls:pits){
            fulls.removeStones();
        }
        initializeBoard();
    }
    public void registerPlayers(final Player one, final Player two){
        // setting up two way connection
        one.setStore(stores.get(0));
        two.setStore(stores.get(1));
        stores.get(0).setOwner(one);
        stores.get(1).setOwner(two);
    }

    public int moveStones(final int startPit, final Player player) throws InvalidMoveException {
        // check exception for invalid move
        if (startPit > 12 || startPit < 1){
            throw new InvalidMoveException("Your move was out of bounds. This is an invalid move!");
        } else if ((startPit >= 1 && startPit <= 6) && player.equals(stores.get(1).getOwner())) {
            throw new InvalidMoveException("You do not have access to this side of the board!");
        } else if ((startPit >= 7 && startPit <= 12) && player.equals(stores.get(0).getOwner())) {
            throw new InvalidMoveException("You do not have access to this side of the board!");
        } else if ((startPit >= 1 && startPit <= 6) && player == (stores.get(1).getOwner())) {
            throw new InvalidMoveException("You do not have access to this side of the board!");
        } else if ((startPit >= 7 && startPit <= 12) && player == (stores.get(0).getOwner())) {
            throw new InvalidMoveException("You do not have access to this side of the board!");
        }
        
        final int beforeDistribute = player.getStoreCount();
        try {
            distributeStones(startPit);
        } catch (PitNotFoundException err){
            System.out.println(err.getMessage());
        }
        final int afterDistribute = player.getStoreCount();
        // check how many were added to store? distribute may do this
        // moveStones takes the difference for players store
        //final int difference = afterDistribute-beforeDistribute;
        // return total stones added to players store
        return afterDistribute-beforeDistribute;
    }
    public int distributeStones(int startingPoint) throws PitNotFoundException{
        if (startingPoint > 12 || startingPoint < 1){
            throw new PitNotFoundException("Pit not found!");
        }
        int whichStore = 0;
        if (startingPoint >= 1 && startingPoint <= 6) {
            whichStore = 1;
        } else if(startingPoint >= 7 && startingPoint <= 12) {
            whichStore = 2;
        }
        int distributing;
        int placeHolder;
        distributing = pits.get(startingPoint-1).removeStones();
        placeHolder = distributing;
        int index = startingPoint-1; //index controls what pit you are on
        while(distributing > 0) {
            index++;
            if(index == 6 && distributing > 0 && whichStore == 1) { //currentPlayer.addStoreCount(currentPlayer, 1);
                stores.get(0).addStones(1);
                distributing--; //i++; // increases i but not index
            }
            if (index == 12 && distributing > 0 && whichStore == 2) {//currentPlayer.addStoreCount(currentPlayer, 1);
                distributing--;
                stores.get(1).addStones(1); //i++;
            }
            if (index >= TWELVE){ // conditions for wrapping
                index = 0;
            }
            if (distributing > 0) {
                pits.get(index).addStone();
                distributing--;
                setFreeMove(0);
            } else {
                setFreeMove(1);
            }
        }
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
        return placeHolder; // returns total number distributed
    }
    public int captureStones(final int stoppingPoint) throws PitNotFoundException{
        // check valid pit exception
        if (stoppingPoint > 12 || stoppingPoint < 1){
            throw new PitNotFoundException("Pit not found!");
        }
        // check opponents corresponding pit to the stopping pit
        final int min = 1;
        final int max = pits.size();
        final int correspondingNum = max - stoppingPoint + min;
        final int captured = pits.get(correspondingNum-1).removeStones(); // put stones in correct store
        if (stoppingPoint >= 1 && stoppingPoint <=6){
            stores.get(0).addStones(captured);
        } else if (stoppingPoint >= 7 && stoppingPoint <=12){
           stores.get(1).addStones(captured);
        }
        return captured; // returns all captured stones
    }
 
    public int getNumStones(final int pitNum) throws PitNotFoundException{
        if (pitNum > 12 || pitNum < 1){
            throw new PitNotFoundException("Pit not found!");
        }
        return pits.get(pitNum-1).getStoneCount();
    }
    
    public boolean isSideEmpty(final int pitNum) throws PitNotFoundException{
        if (pitNum > 12 || pitNum < 1){
            throw new PitNotFoundException("Pit not found!");
        }
        try {
        if (pitNum >= 1 && pitNum <= 6) {
            for (int i = 1; i <= 6; i++){
                if(getNumStones(i) != 0){
                    return false;
                }
            }
        } else if (pitNum >= 7 && pitNum <= 12) {
            for(int i = 7; i <= 12; i++){
                if(getNumStones(i) != 0){
                    return false;
                }
            }
        }
        } catch (PitNotFoundException err){
            System.out.println(err.getMessage());
        }
        return true;
    }
    public int getSideCount(final int pitNum) throws PitNotFoundException{
        if (pitNum > 12 || pitNum < 1) {
            throw new PitNotFoundException("Pit not found!");
        }
        int total = 0;
            if (pitNum >= 1 && pitNum <= 6) {
                for (int i = 1; i <= 6; i++){
                    total += getNumStones(i);
                }
            } else if (pitNum >= 7 && pitNum <= 12) {
                for (int i = 7; i <= 12; i++){
                    total += getNumStones(i);
                }
            }
        
        return total;
    }

    public void setFreeMove(final int set) {
        freeMove = set;
    }
    public int getFreeMove() {
        return freeMove;
    }

    @Override
    public String toString(){
        int count = 1;
        final StringBuilder boardString = new StringBuilder("Board\n");
        for (int i = 1; i <= 12; i++) {
            boardString.append("Pit ").append(i).append(": ").append(pits.get(i - 1)).append("\n");
            if (i == TWELVE/2) {
                boardString.append("Store " + count++ + ":").append(stores.get(0)).append("\n");
            } else if (i == TWELVE) {
                boardString.append("Store " + count++ + ":").append(stores.get(1)).append("\n");
            }
        }
        return boardString.toString();
    }
    
}
