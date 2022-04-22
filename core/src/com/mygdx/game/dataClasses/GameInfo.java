package com.mygdx.game.dataClasses;

import java.util.List;

public class GameInfo {

    public int gameId;
    public List<Player> players;
    public String hostId;
    public String difficulty;
    public int rounds;

    public GameInfo(int gameId, List<Player> players, String hostId, String difficulty, int rounds) {
        this.gameId = gameId;
        this.players = players;
        this.hostId = hostId;
        this.difficulty = difficulty;
        this.rounds = rounds;
    }

    @Override
    public String toString() {
        return "GameInfo{" +
                "gameId=" + gameId +
                ", players=" + players +
                ", host='" + hostId + '\'' +
                '}';
    }
}
