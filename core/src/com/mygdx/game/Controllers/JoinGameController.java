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

    private final ViewManager viewManager;

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

    public String validateInput(String gamePin, String nickname) {
        if (!gamePin.equals("") && !nickname.equals("")) {
            try {
                this.joinGame(Integer.parseInt(gamePin), nickname);
                return "";
            } catch (Exception e) {
                return "Not valid Game Pin";
            }
        } else if (gamePin.equals("") && !nickname.equals("")) {
            return "Fill in Game Pin";
        } else if (!gamePin.equals("") && nickname.equals("")) {
            try {
                Double.parseDouble(gamePin);
                return "Fill in nickname";
            } catch (NumberFormatException e) {
                return "Fill in nickname and \n   valid Game Pin";
            }
        } else {
            return "Fill in Game Pin and nickname";
        }
    }
}
