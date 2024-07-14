package ui;

import mancala.MancalaGame;
import mancala.InvalidMoveException;
import mancala.Player;
import mancala.Saver;
import java.io.IOException;

import java.io.Serializable;
import java.util.Scanner;

public class TextUI implements Serializable{

    private MancalaGame textGame;
    private MancalaGame textTest;
    private Scanner scanner;
    private Player playerOne;
    private Player playerTwo;

    public TextUI() {
        textGame = new MancalaGame();
        scanner = new Scanner(System.in);
        playerOne = new Player("Player 1");
        playerTwo = new Player("Player 2");
    }
    public static void main(String[] args){
        TextUI textUI = new TextUI();
        textUI.setPlayers();
        textUI.playGame();
        try {
            textUI.saveObj();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            textUI.loadObj();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Testing serialization");
        System.out.println(textUI.toString());
    }

    public void playGame() {
        int userInput = 0;
        while (!isGameOver()){

            userInput = getUserInput();

            try {
                makeMove(userInput, getCurrentPlayer());
                System.out.println(textGame.toString());
                if (getCurrentPlayer() == playerOne) {
                    if(textGame.getBoard().getFreeMove() == 1) {
                        setCurrentPlayer(playerOne);
                    } else {
                        setCurrentPlayer(playerTwo);
                    }
                } else if (getCurrentPlayer() == playerTwo) {
                    if(textGame.getBoard().getFreeMove() == 1) {
                        setCurrentPlayer(playerTwo);
                    } else {
                        setCurrentPlayer(playerOne);
                    }
                } 
            } catch (InvalidMoveException err) {
                System.out.println(err.getMessage());
                if (getCurrentPlayer() == playerOne) {
                    setCurrentPlayer(playerOne);
                } else if (getCurrentPlayer() == playerTwo) {
                    setCurrentPlayer(playerTwo);
                }
            }
            
            System.out.println(textGame.getCurrentPlayer());
            System.out.println("Current player = " + textGame.getCurrentPlayer());

        }
        
    }

    public void saveObj() throws IOException{
        Saver.saveObject(textGame, "test.txt");
    }

    public Serializable loadObj() throws IOException, ClassNotFoundException{
        textTest = (MancalaGame) Saver.loadObject("test.txt");
        return textTest;
    }

    public int getUserInput(){
        int userInput = 0;
        if (textGame.getCurrentPlayer() == textGame.getPlayers().get(0)){
            System.out.println("Player #1: enter a number from 1-6");
        } else if (textGame.getCurrentPlayer() == textGame.getPlayers().get(1)) {
            System.out.println("Player #2: enter a number from 7-12");
        }
        System.out.println("Please enter a number corresponding to your side:");
        userInput = scanner.nextInt();
        return userInput;
    }

    public void makeMove(int userInput, Player currentPlayer) throws InvalidMoveException{
        //try {   
        textGame.move(userInput);
        //} catch (Exception err) {
            //System.out.println(err.getMessage());
        //}
    }

    /*public void test(int pitNum) throws PitNotFoundException{
       textGame.test(); 
    }*/

    public void setPlayers() {
        textGame.setPlayers(playerOne, playerTwo);

    }

    public boolean isGameOver(){
        return textGame.isGameOver();
    }

    public Player getCurrentPlayer(){
        return textGame.getCurrentPlayer();
    }

    public void setCurrentPlayer(Player player) {
        textGame.setCurrentPlayer(player);
    }

    @Override
    public String toString(){
        return textGame.toString();
    }

}