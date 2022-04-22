package com.mygdx.game.Controllers;

import com.google.gson.Gson;
import com.mygdx.game.ColorSplash;
import com.mygdx.game.Events.EventsConstants;
import com.mygdx.game.Views.GetReadyView;
import com.mygdx.game.Views.MainMenuView;
import com.mygdx.game.Views.ViewManager;
import com.mygdx.game.dataClasses.ScoreBoardInfo;

import io.socket.emitter.Emitter;

public class ScoreBoardController {

    private ScoreBoardInfo scoreBoardInfo;
    private boolean isHost;
    private boolean isLoading = true;
    private ViewManager viewManager;

    public ScoreBoardController(ViewManager vm){
        this.roundFinished();
        this.viewManager = vm;
    }

    public void roundFinished() {
        ColorSplash.socketManager.createListener(EventsConstants.getEndRoundResult, scoreBoardListener());
    }

    public void nextRound(int gameId) {
        ColorSplash.socketManager.nextRound(gameId);
    }

    public void endGame(int gameId) {ColorSplash.socketManager.endGame(gameId);}

    public Emitter.Listener scoreBoardListener() {
        return new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                isLoading = true;
                Gson gson = new Gson();
                scoreBoardInfo = gson.fromJson(args[0].toString(), ScoreBoardInfo.class);
                isHost = scoreBoardInfo.hostId.equals(ColorSplash.socketManager.getSocketId());
                isLoading = false;
            }
        };
    }

    public ScoreBoardInfo getScoreBoardInfo() {
        return scoreBoardInfo;
    }

    public boolean isHost() {
        return isHost;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setGetReadyView() {
        viewManager.set(new GetReadyView(viewManager));
    }

    public void setMainMenuView() {
        viewManager.set(new MainMenuView(viewManager));
    }
}