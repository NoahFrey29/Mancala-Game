package mancala;

import java.io.Serializable;

public class Store implements Countable, Serializable{

    private static final long serialVersionUID = 8197403145304665358L;

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

    @Override
    public void addStones(final int amount) {
        storeStones += amount;
    }
    public int emptyStore(){
        final int temp = storeStones;
        storeStones = 0;
        return temp;
    }
    @Override
    public void addStone(){
        storeStones++;
    }
    @Override
    public int getStoneCount(){
        return storeStones;
    }
    @Override
    public int removeStones(){
        return emptyStore();
    }

    @Override
    public String toString() {
        return "Store owned by " + playerUser.getName() + " with " + storeStones + " stones";
    }

}