package com.mygdx.game.Controllers;

import com.google.gson.Gson;
import com.mygdx.game.ColorSplash;
import com.mygdx.game.Events.EventsConstants;
import com.mygdx.game.Views.GetReadyView;
import com.mygdx.game.Views.MainMenuView;
import com.mygdx.game.Views.View;
import com.mygdx.game.Views.ViewManager;
import com.mygdx.game.dataClasses.GameInfo;

import io.socket.emitter.Emitter;

public class GameLobbyController {

    public GameInfo gameInfo;
    public boolean isHost;
    public boolean isLoading = true;
    private ViewManager viewManager;

    public GameLobbyController(ViewManager viewManager) {
        this.gameCreated();
        this.gameStarted();
        this.viewManager = viewManager;
    }

    public void gameCreated() {
        ColorSplash.socketManager.createListener(EventsConstants.gameInfo, joe());
    }

    public void gameStarted() {
        ColorSplash.socketManager.createListener(EventsConstants.displayColors, gameHasStarted());
    }

    public void startGame(int gameId) {
        ColorSplash.socketManager.startGame(gameId);
    }

    public Emitter.Listener gameHasStarted() {
        return new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                //setGetReadyView();
            }
        };
    }

    public Emitter.Listener joe() {
        return new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                isLoading = true;
                Gson gson = new Gson();
                gameInfo = gson.fromJson(args[0].toString(), GameInfo.class);
                isHost = gameInfo.hostId.equals(ColorSplash.socketManager.getSocketId());
                isLoading = false;

            }
        };
    }

    public void setMainMenuView() {
        viewManager.set(new MainMenuView(viewManager));
    }

    public void setGetReadyView() {
        viewManager.set(new GetReadyView(viewManager));
    }
}
