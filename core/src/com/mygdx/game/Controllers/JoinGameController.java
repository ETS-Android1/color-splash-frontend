package com.mygdx.game.Controllers;

import com.mygdx.game.ColorSplash;
import com.mygdx.game.Views.GameLobbyView;
import com.mygdx.game.Views.MainMenuView;
import com.mygdx.game.Views.ViewManager;

public class JoinGameController {

    private ViewManager viewManager;

    public JoinGameController(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void joinGame(int gameId, String nickname) {
        ColorSplash.socketManager.joinGame(gameId, nickname);
    }

    public void setGameLobbyView() {
        viewManager.set(new GameLobbyView(viewManager));
    }

    public void setMainMenuView() {
        viewManager.set(new MainMenuView(viewManager));
    }

}
