package mancala;

import java.io.Serializable;

public class Player implements Serializable{

    private String playerName;
    private Store playerStore;

    private static final long serialVersionUID = -6913182525731495042L;

    public Player(){
        playerName = "Default player";
    }
    public Player(final String name) {
        playerName = name;
    }

    public String getName(){
        return playerName;
    }
    /* default */ void setName(final String name){
        playerName = name;
    }

    /* default */ void setStore(final Store store){
        playerStore = store;
    }
    public Store getStore() {
        return playerStore;
    }
    public int getStoreCount() {
        return playerStore.getStoneCount();
    }

    public void addStoreCount(final Player player, final int amount) {
        final Store incStore = player.getStore();
        incStore.addStones(amount);
    }

    @Override
    public String toString() {
        return "Player: " + playerName;// + "\nStore: " + playerStore.getStoneCount(); // gives error
    }

}