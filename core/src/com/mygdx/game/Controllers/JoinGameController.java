package com.mygdx.game.Controllers;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.ColorSplash;
import com.mygdx.game.Events.EventsConstants;
import com.mygdx.game.Views.GameLobbyView;
import com.mygdx.game.Views.MainMenuView;
import com.mygdx.game.Views.ViewManager;

import io.socket.emitter.Emitter;

public class JoinGameController{

    private ViewManager viewManager;

    public JoinGameController(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void joinGame(int gameId, String nickname) {
        ColorSplash.socketManager.joinGame(gameId, nickname);
    }

    public void displayColors() {
        ColorSplash.socketManager.createListener(EventsConstants.gameInfo, joinListener());
    }

    public Emitter.Listener joinListener() {
        return new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Gdx.app.postRunnable (new Runnable() {
                    @Override
                    public void run() {
                        setGameLobbyView();
                    }
                });
            }
        };
    }

    public void setGameLobbyView() {
        viewManager.set(new GameLobbyView(viewManager));
    }

    public void setMainMenuView() {
        viewManager.set(new MainMenuView(viewManager));
    }

}
