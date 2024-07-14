package mancala;

import java.io.Serializable;

public class UserProfile implements Serializable{
    
    private static final long serialVersionUID = -594346698379271433L;

    private String userName;
    private int kalahGames;
    private int ayoGames;
    private int kalahWon;
    private int ayoWon;

    public UserProfile(){
        userName = "Default User";
        kalahGames = 0;
        ayoGames = 0;
        kalahWon = 0;
        ayoWon = 0;
    }

    public String getUsername(){
        return userName;
    }
    /* default */ void setUsername(final String name){
        userName = name;
    }
    public int getKalahPlayed(){
        return kalahGames;
    }
    /* default */ void setKalahPlayed(final int games){
        kalahGames = games;
    }
    public int getAyoPlayed(){
        return ayoGames;
    }
    /* default */ void setAyoPlayed(final int games){
        ayoGames = games;
    }
    public int getKalah(){
        return kalahWon;
    }
    /* default */ void setkalahWon(final int wins){
        kalahWon = wins;
    }
    public int getAyo(){
        return ayoWon;
    }
    /* default */ void setayoWon(final int wins){
        ayoWon = wins;
    }
    
    

}
