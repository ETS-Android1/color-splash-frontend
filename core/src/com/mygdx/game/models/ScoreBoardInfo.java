package com.mygdx.game.models;

import java.util.List;

public class ScoreBoardInfo {

    private int gameId;
    private int round;
    private int maxRound;
    private List<Result> result;
    private String hostId;

    public int getGameId() {
        return gameId;
    }

    public int getRound() {
        return round;
    }

    public int getMaxRound() {
        return maxRound;
    }

    public List<Result> getResult() {
        return result;
    }

    public String getHostId() {
        return hostId;
    }
}
