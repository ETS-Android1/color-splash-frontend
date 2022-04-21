package com.mygdx.game.controllers;

import com.google.gson.Gson;
import com.mygdx.game.ColorSplash;
import com.mygdx.game.Events.EventsConstants;
import com.mygdx.game.dataClasses.GameCreated;

import java.util.Arrays;

import io.socket.emitter.Emitter;

public class GameLobbyController {

    public void gameCreated() {
        ColorSplash.socketManager.createListener(EventsConstants.gameCreated, joe());
    }


    public Emitter.Listener joe() {
        return new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println(Arrays.toString(args));
                Gson gson = new Gson(); // Or use new GsonBuilder().create();
                GameCreated target2 = gson.fromJson(Arrays.toString(args), GameCreated.class);
            }
        };
    }
}
