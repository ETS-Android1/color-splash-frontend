package com.mygdx.game.dataClasses;

import java.util.List;

public class GameCreated {

    public int gameId;
    public List<Player> players;

    @Override
    public String toString() {
        return "GameCreated{" +
                "gameId=" + gameId +
                ", players=" + players +
                '}';
    }
}
