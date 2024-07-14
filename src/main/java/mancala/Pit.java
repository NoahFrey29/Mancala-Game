package mancala;

import java.io.Serializable;

public class Pit implements Countable, Serializable{

    private int stoneCount;

    private static final long serialVersionUID = -5900112831982566551L;

    public Pit(){
        stoneCount = 0;
    }
    @Override
    public int getStoneCount() {
        return stoneCount;
    }
    @Override
    public void addStone(){
        stoneCount++;
    }
    @Override
    public int removeStones() {
        final int temp = stoneCount;
        stoneCount = 0;
        return temp;
    }
    @Override
    public void addStones(final int numToAdd){
        stoneCount += numToAdd;
    }

    @Override
    public String toString() {
        return "Pit with " + stoneCount + " stones";
    }

}