package com.mygdx.game.controllers;

import com.mygdx.game.ColorSplash;
import com.mygdx.game.views.MainMenuView;
import com.mygdx.game.views.ViewManager;
import com.mygdx.game.models.ScoreBoardInfo;

public class ScoreBoardController {

    private final ScoreBoardInfo scoreBoardInfo;
    private final boolean isHost;
    private final boolean isLastRound;
    private final ViewManager viewManager;

    public ScoreBoardController(ViewManager viewManager, ScoreBoardInfo scoreBoardInfo){
        this.viewManager = viewManager;
        this.scoreBoardInfo = scoreBoardInfo;
        this.isHost = scoreBoardInfo.getHostId().equals(ColorSplash.socketManager.getSocketId());
        this.isLastRound = scoreBoardInfo.getRound() == scoreBoardInfo.getMaxRound();
    }

    public void nextRound(int gameId) {
        ColorSplash.socketManager.nextRound(gameId);
    }

    public void endGame(int gameId) {
        ColorSplash.socketManager.endGame(gameId);
        setMainMenuView();
    }

    public ScoreBoardInfo getScoreBoardInfo() {
        return scoreBoardInfo;
    }

    public boolean isHost() {
        return isHost;
    }

    public boolean isLastRound() {
        return isLastRound;
    }

    public void setMainMenuView() {
        viewManager.set(new MainMenuView(viewManager));
    }
}