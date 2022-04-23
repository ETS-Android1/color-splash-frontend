package com.mygdx.game.Controllers;

import com.mygdx.game.ColorSplash;
import com.mygdx.game.Events.EventsConstants;
import com.mygdx.game.Views.GameLobbyView;
import com.mygdx.game.Views.MainMenuView;
import com.mygdx.game.Views.ViewManager;

import io.socket.emitter.Emitter;

public class CreateGameController extends ErrorController{

    public CreateGameController(ViewManager viewManager) {
        super(viewManager);
    }

    public void createGame(String nickname, int rounds, String difficulty, int maxPlayers) {
        ColorSplash.socketManager.createGame(nickname, rounds, difficulty, maxPlayers);
    }

    public void setGameLobbyView() {
        viewManager.set(new GameLobbyView(viewManager));
    }

    public void setMainMenuView() {
        viewManager.set(new MainMenuView(viewManager));
    }
}
