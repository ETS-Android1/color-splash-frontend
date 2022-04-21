package com.mygdx.game.Controllers;

import com.mygdx.game.ColorSplash;

public class JoinGameController {

    public void joinGame(int gameId, String nickname) {
        ColorSplash.socketManager.joinGame(gameId, nickname);
    }
}
