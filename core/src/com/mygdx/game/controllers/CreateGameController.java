package com.mygdx.game.controllers;

import com.mygdx.game.ColorSplash;
import com.mygdx.game.views.GameLobbyView;
import com.mygdx.game.views.MainMenuView;
import com.mygdx.game.ViewManager;

public class CreateGameController {

    private final ViewManager viewManager;

    public CreateGameController(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void createGame(String nickname, int rounds, String difficulty, int maxPlayers) {
        ColorSplash.socketManager.createGame(nickname, rounds, difficulty, maxPlayers);
        setGameLobbyView();
    }

    public void setGameLobbyView() {
        viewManager.set(new GameLobbyView(viewManager, true));
    }

    public void setMainMenuView() {
        viewManager.set(new MainMenuView(viewManager));
    }
}
