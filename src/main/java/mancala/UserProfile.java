package mancala;

import java.io.Serializable;

public class UserProfile implements Serializable{
    
    private String userName;
    private int kalahGames;
    private int ayoGames;
    private int KalahWon;
    private int AyoWon;

    public UserProfile(){
        userName = "Default User";
        kalahGames = 0;
        ayoGames = 0;
        KalahWon = 0;
        AyoWon = 0;
    }

    public String getUsername(){
        return userName;
    }
    void setUsername(String name){
        userName = name;
    }
    public int getKalahPlayed(){
        return kalahGames;
    }
    void setKalahPlayed(int games){
        kalahGames = games;
    }
    public int getAyoPlayed(){
        return ayoGames;
    }
    void setAyoPlayed(int games){
        ayoGames = games;
    }
    public int getKalah(){
        return KalahWon;
    }
    void setKalahWon(int wins){
        KalahWon = wins;
    }
    public int getAyo(){
        return AyoWon;
    }
    void setAyoWon(int wins){
        AyoWon = wins;
    }
    
    

}
