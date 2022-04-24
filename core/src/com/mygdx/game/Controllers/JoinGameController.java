package com.mygdx.game.Controllers;

import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import com.mygdx.game.ColorSplash;
import com.mygdx.game.Events.EventsConstants;
import com.mygdx.game.Views.GameLobbyView;
import com.mygdx.game.Views.MainMenuView;
import com.mygdx.game.Views.ViewManager;
import com.mygdx.game.dataClasses.GameInfo;

import io.socket.emitter.Emitter;

public class JoinGameController{

    private ViewManager viewManager;

    public JoinGameController(ViewManager viewManager) {
        this.viewManager = viewManager;
        this.createJoinListener();
    }

    public void joinGame(int gameId, String nickname) {
        ColorSplash.socketManager.joinGame(gameId, nickname);
    }

    public void createJoinListener() {
        ColorSplash.socketManager.createListener(EventsConstants.gameInfo, joinListener());
    }

    public Emitter.Listener joinListener() {
        return new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                Gdx.app.postRunnable (new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        GameInfo gameInfo = gson.fromJson(args[0].toString(), GameInfo.class);
                        setGameLobbyView(gameInfo);
                    }
                });
            }
        };
    }

    public void setGameLobbyView(GameInfo gameInfo) {
        ColorSplash.socketManager.removeEventListener(EventsConstants.gameInfo);
        viewManager.set(new GameLobbyView(viewManager, false, gameInfo));
    }

    public void setMainMenuView() {
        ColorSplash.socketManager.removeEventListener(EventsConstants.gameInfo);
        viewManager.set(new MainMenuView(viewManager));
    }

}
