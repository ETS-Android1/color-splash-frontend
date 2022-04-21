package com.mygdx.game.controllers;

import com.mygdx.game.ColorSplash;

public class JoinGameController {

    public void joinGame(int gameId, String nickname) {
        ColorSplash.socketManager.joinGame(gameId, nickname);
    }
}
