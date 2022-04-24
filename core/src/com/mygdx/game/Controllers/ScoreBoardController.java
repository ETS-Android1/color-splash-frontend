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
    private ViewManager viewManager;

    public ScoreBoardController(ViewManager viewManager, ScoreBoardInfo scoreBoardInfo){
        this.viewManager = viewManager;
        this.scoreBoardInfo = scoreBoardInfo;
        this.isHost = scoreBoardInfo.getHostId().equals(ColorSplash.socketManager.getSocketId());
    }

    public void nextRound(int gameId) {
        ColorSplash.socketManager.nextRound(gameId);
    }

    public void endGame(int gameId) {ColorSplash.socketManager.endGame(gameId);}

    public ScoreBoardInfo getScoreBoardInfo() {
        return scoreBoardInfo;
    }

    public boolean isHost() {
        return isHost;
    }

    public void setMainMenuView() {
        viewManager.set(new MainMenuView(viewManager));
    }
}