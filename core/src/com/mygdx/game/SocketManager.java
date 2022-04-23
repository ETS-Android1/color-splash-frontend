package com.mygdx.game;

import com.google.gson.Gson;
import com.mygdx.game.Events.EventsConstants;

import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class SocketManager {
    private Socket socket;
    public SocketManager(){
        try {
			socket = IO.socket("https://color-splash.herokuapp.com");
            socket.connect();
		} catch ( URISyntaxException e) {
			e.printStackTrace();
		}
    }

    public void createListener(String event, Emitter.Listener listener) {
        this.socket.on(event, listener);
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

    public void startGame(int gameId) {
        JSONObject json = new JSONObject();
        json.put("gameId", gameId);
        this.socket.emit(EventsConstants.startGame, json);
    }

    public void nextRound(int gameId) {
        JSONObject json = new JSONObject();
        json.put("gameId", gameId);
        this.socket.emit(EventsConstants.nextRound, json);
    }

    public void colorsDisplayFinished(int gameId) {
        JSONObject json = new JSONObject();
        json.put("gameId", gameId);
        this.socket.emit(EventsConstants.colorsDisplayedFinished, json);
    }

    public void playerFinished(int gameId, List<Integer> answer) {
        String answerString = "";
        for (Integer answerInteger : answer) {
            answerString += answerInteger.toString();
        }
        JSONObject json = new JSONObject();
        json.put("gameId", gameId);
        json.put("answer", answerString);
        this.socket.emit(EventsConstants.playerFinished, json);
    }

    public void endGame(int gameId){
        JSONObject json = new JSONObject();
        json.put("gameId", gameId);
        this.socket.emit(EventsConstants.endGame, json);
    }


    public String getSocketId() {
        return socket.id();
    }

    public void leaveGame(int gameId) {
        JSONObject json = new JSONObject();
        json.put("gameId", gameId);
        this.socket.emit(EventsConstants.leaveGame, json);
    }

}