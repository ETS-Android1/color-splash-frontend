package com.mygdx.game.controllers;

import com.google.gson.Gson;
import com.mygdx.game.ColorSplash;
import com.mygdx.game.Events.EventsConstants;
import com.mygdx.game.Views.GameLobbyView;
import com.mygdx.game.dataClasses.GameInfo;
import com.mygdx.game.dataClasses.Player;

import java.util.ArrayList;
import java.util.Arrays;

import io.socket.emitter.Emitter;

public class GameLobbyController {

    public GameInfo gameInfo;
    public boolean isHost;
    public boolean isLoading = true;

    public void gameCreated() {
        ColorSplash.socketManager.createListener(EventsConstants.gameInfo, joe());
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
}
