package mancala;

import java.io.Serializable;

public class Pit implements Countable, Serializable{

    private int stoneCount;

    public Pit(){
        stoneCount = 0;
    }

    public int getStoneCount() {
        return stoneCount;
    }
    public void addStone(){
        stoneCount++;
    }
    public int removeStones() {
        final int temp = stoneCount;
        stoneCount = 0;
        return temp;
    }
    public void addStones(int numToAdd){
        stoneCount += numToAdd;
    }

    @Override
    public String toString() {
        return "Pit with " + stoneCount + " stones";
    }

}