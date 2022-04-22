package com.mygdx.game.dataClasses;

public class TimesUp {
    private int gameId;
    private int rounds;

    public TimesUp(int gameId) {
        this.gameId = gameId;
    }

    @Override
    public String toString() {
        return "TimesUp{" +
                "gameId=" + gameId +
                ", rounds=" + rounds +
                '}';
    }
}
