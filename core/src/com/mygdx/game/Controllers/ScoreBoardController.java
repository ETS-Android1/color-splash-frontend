package com.mygdx.game.Controllers;

import com.mygdx.game.ColorSplash;
import com.mygdx.game.Views.GetReadyView;
import com.mygdx.game.Views.MainMenuView;
import com.mygdx.game.Views.ViewManager;

public class ScoreBoardController {

    private ViewManager viewManager;

    public ScoreBoardController(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void scoreBoard(String nickname, String playerId, int totalScore, int avatarIndex) {
        //ColorSplash.socketManager.getEndRoundResult(nickname, playerId, totalScore, avatarIndex);
    }

    public void setGetReadyView() {
        viewManager.set(new GetReadyView(viewManager));
    }

    public void setMainMenuView() {
        viewManager.set(new MainMenuView(viewManager));
    }
}
