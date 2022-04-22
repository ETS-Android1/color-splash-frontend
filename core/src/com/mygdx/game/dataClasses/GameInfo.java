package com.mygdx.game.dataClasses;

import java.util.List;

public class GameInfo {

    public int gameId;
    public List<Player> players;
    public String hostId;

    public GameInfo(int gameId, List<Player> players, String hostId) {
        this.gameId = gameId;
        this.players = players;
        this.hostId = hostId;
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
