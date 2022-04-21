package com.mygdx.game.controllers;

import com.google.gson.Gson;
import com.mygdx.game.ColorSplash;
import com.mygdx.game.Events.EventsConstants;
import com.mygdx.game.dataClasses.GameInfo;
import com.mygdx.game.dataClasses.Player;

import java.util.ArrayList;

import io.socket.emitter.Emitter;

public class GameLobbyController {

    public GameInfo gameInfo;
    public boolean isHost;

    public GameLobbyController() {
        gameInfo = new GameInfo(0, new ArrayList<Player>(), "");
    }

    public void gameCreated() {
        ColorSplash.socketManager.createListener(EventsConstants.gameInfo, joe());
    }

    public Emitter.Listener joe() {
        return new Emitter.Listener() {
            @Override
            public void call(Object... args) {

                System.out.println(args[0]);
                Gson gson = new Gson();
                gameInfo = gson.fromJson(args[0].toString(), GameInfo.class);
                System.out.println(gameInfo);
                isHost = gameInfo.hostId.equals(ColorSplash.socketManager.getSocketId());

            }
        };
    }
}
