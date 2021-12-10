package com.example.databasev2;

public class Game {

    private int gameID;
    private String title;
    private String price;
    private String description;

    public Game() {
        gameID = -1;
    }

    public int getGameID() { return gameID; }

    public void setGameID(int gameID) { this.gameID = gameID; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getPrice() { return price; }

    public void setPrice(String price) { this.price = price; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }


}
