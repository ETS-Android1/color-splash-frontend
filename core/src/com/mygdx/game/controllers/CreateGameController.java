package com.mygdx.game.controllers;

import com.mygdx.game.ColorSplash;

public class CreateGameController {

    public void createGame(String nickname, int rounds, String difficulty, int maxPlayers) {
        ColorSplash.socketManager.createGame(nickname, rounds, difficulty, maxPlayers);
    }
}
