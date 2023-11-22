package mancala;

import java.io.Serializable;

public class Player implements Serializable{

    private String playerName;
    private Store playerStore;

    public Player(){
        playerName = "Default player";
    }
    public Player(final String name) {
        playerName = name;
    }

    public String getName(){
        return playerName;
    }
    void setName(final String name){
        playerName = name;
    }

    void setStore(final Store store){
        playerStore = store;
    }
    public Store getStore() {
        return playerStore;
    }
    public int getStoreCount() {
        return playerStore.getTotalStones();
    }

    public void addStoreCount(final Player player, final int amount) {
        final Store incStore = player.getStore();
        incStore.addStones(amount);
    }

    @Override
    public String toString() {
        return "Player: " + playerName + "\nStore: " + playerStore;
    }

}