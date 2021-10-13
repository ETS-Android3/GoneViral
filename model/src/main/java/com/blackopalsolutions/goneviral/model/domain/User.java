package com.blackopalsolutions.goneviral.model.domain;

public class User {
    private int id;
    private String username;
    private String password;
    private int gamesWon;
    private int gamesLost;

    public User() {}

    public User(int id, String username, String password, int gamesWon, int gamesLost) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.gamesWon = gamesWon;
        this.gamesLost = gamesLost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public int getGamesLost() {
        return gamesLost;
    }

    public void setGamesLost(int gamesLost) {
        this.gamesLost = gamesLost;
    }
}
