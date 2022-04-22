package com.mygdx.game.Controllers;

import com.mygdx.game.Views.ScoreBoardView;
import com.mygdx.game.Views.ViewManager;

public class AnswerController {
    private ViewManager viewManager;

    public AnswerController(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void setScoreBoardView() {
        viewManager.set(new ScoreBoardView(viewManager));
    }
}
