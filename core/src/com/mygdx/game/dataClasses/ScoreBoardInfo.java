package com.mygdx.game.dataClasses;

import java.util.List;

public class ScoreBoardInfo {

    public int gameId;
    public int round;
    public int maxRound;
    public List<Result> result;
    public String hostId;

    public ScoreBoardInfo (int gameId, int round, int maxRound, List<Result> result, String hostId) {
        this.gameId = gameId;
        this.round = round;
        this.maxRound = maxRound;
        this.result = result;
        this.hostId = hostId;
    }

    @Override
    public String toString() {
        return "ScoreBoardInfo{" +
                "gameId=" + gameId +
                ", round=" + round +
                ", maxRound=" + maxRound +
                ", result=" + result +
                ", host='" + hostId + '\'' +
                '}';
    }

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
