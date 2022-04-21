package com.mygdx.game;

import com.mygdx.game.Events.EventsConstants;

import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class SocketManager {
    private Socket socket;
    public SocketManager(){
        try {
			socket = IO.socket("https://color-splash.herokuapp.com");
            socket.connect();
            //socket.on(EventsConstants.error, ...);
            //socket.on(EventsConstants.gameCreated, ...);
            //socket.on(EventsConstants.gameInfo, ...);
            //socket.on(EventsConstants.displayColors, ...);
            //socket.on(EventsConstants.roundStarted, ...);
            //socket.on(EventsConstants.timesUp, ...);
            //socket.on(EventsConstants.endRound, ...);
            //socket.on(EventsConstants.gameFinished, ...);
            //socket.on(EventsConstants.gameDeleted, ...);
		} catch ( URISyntaxException e) {
			e.printStackTrace();
		}

    }

    public void createGame(String nickname, int rounds, String difficulty, int maxPlayers) {
        JSONObject json = new JSONObject();
        json.put("nickname", nickname);
        json.put("rounds", rounds);
        json.put("difficulty", difficulty);
        json.put("maxPlayers", maxPlayers);
        this.socket.emit(EventsConstants.hostCreateGame, json);
    }

    public void joinGame(int gameId, String nickname) {
        JSONObject json = new JSONObject();
        json.put("gameId", gameId);
        json.put("nickname", nickname);
        this.socket.emit(EventsConstants.joinGame, json);
    }

}