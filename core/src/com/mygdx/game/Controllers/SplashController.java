package com.mygdx.game.Controllers;

import com.mygdx.game.Views.AnswerView;
import com.mygdx.game.Views.ViewManager;

public class SplashController {
    private ViewManager viewManager;

    public SplashController(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void setAnswerView() {
        viewManager.set(new AnswerView(viewManager));
    }
}
