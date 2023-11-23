package mancala;

import java.io.Serializable;

public class Store implements Countable, Serializable{

    private Player playerUser;
    private int storeStones;

    public Store(){
        storeStones = 0;
        playerUser = new Player();
    }

    void setOwner(final Player player){
        playerUser = player;
    }
    public Player getOwner(){
        return playerUser;
    }

    public void addStones(final int amount) {
        storeStones += amount;
    }
    public int emptyStore(){
        final int temp = storeStones;
        storeStones = 0;
        return temp;
    }
    public void addStone(){
        storeStones++;
    }
    public int getStoneCount(){
        return storeStones;
    }
    public int removeStones(){
        return 0;
    }

    @Override
    public String toString() {
        return "Store owned by " + playerUser.getName() + " with " + storeStones + " stones";
    }

}