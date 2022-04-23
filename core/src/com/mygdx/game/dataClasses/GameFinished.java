package com.mygdx.game.dataClasses;

import java.util.List;

public class GameFinished {
    private int gameId;
    private List<Result> results;
    private int rounds;
    private int maxRounds;

    public GameFinished(int gameId) {
        this.gameId = gameId;
    }

    @Override
    public String toString() {
        return "GameFinished{" +
                "gameId=" + gameId +
                ", results=" + results +
                ", rounds=" + rounds +
                ", maxRounds=" + maxRounds +
                '}';
    }
}
