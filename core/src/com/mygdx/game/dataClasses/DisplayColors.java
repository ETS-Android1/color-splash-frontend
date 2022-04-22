package com.mygdx.game.dataClasses;

import java.util.List;

public class DisplayColors {
    private int gameId;
    private int round;
    private int maxRounds;
    private List<Integer> number;


    public DisplayColors(int gameId, int round, int maxRounds, List<Integer> number) {
        this.gameId = gameId;
        this.round = round;
        this.maxRounds = maxRounds;
        this.number = number;
    }

    @Override
    public String toString() {
        return "DisplayColors{" +
                "gameId=" + gameId +
                ", round=" + round +
                ", maxRounds=" + maxRounds +
                ", number=" + number +
                '}';
    }

}