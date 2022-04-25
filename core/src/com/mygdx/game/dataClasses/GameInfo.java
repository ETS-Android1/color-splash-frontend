package com.mygdx.game.dataClasses;

import java.util.List;

public class GameInfo {

    private int gameId;
    private List<Player> players;
    private String hostId;
    private String difficulty;
    private int rounds;

    public int getGameId() {
        return gameId;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public String getHostId() {
        return hostId;
    }

    public int getRounds() {
        return rounds;
    }

    public String getDifficulty() {
        return difficulty;
    }
}
