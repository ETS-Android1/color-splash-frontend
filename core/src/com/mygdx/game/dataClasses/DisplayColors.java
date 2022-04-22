package com.mygdx.game.dataClasses;

import java.util.List;

public class DisplayColors {
    private int gameId;
    private int round;
    private int maxRounds;
    private List<Integer> colors;


    public DisplayColors(int gameId, int round, int maxRounds, List<Integer> colors) {
        this.gameId = gameId;
        this.round = round;
        this.maxRounds = maxRounds;
        this.colors = colors;
    }


    public int getGameId() {
        return gameId;
    }

    public int getRound() {
        return round;
    }

    public int getMaxRounds() {
        return maxRounds;
    }

    public List<Integer> getNumber() {
        return colors;
    }

    @Override
    public String toString() {
        return "DisplayColors{" +
                "gameId=" + gameId +
                ", round=" + round +
                ", maxRounds=" + maxRounds +
                ", colors=" + colors +
                '}';
    }
}