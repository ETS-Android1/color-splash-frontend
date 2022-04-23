package com.mygdx.game.dataClasses;

public class GameDeleted {
    private int gameId;

    public GameDeleted(int gameId) {
        this.gameId = gameId;
    }

    @Override
    public String toString() {
        return "GameDeleted{" +
                "gameId=" + gameId +
                '}';
    }
}
