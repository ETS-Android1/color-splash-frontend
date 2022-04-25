package com.mygdx.game.controllers;

import com.mygdx.game.views.MainMenuView;
import com.mygdx.game.ViewManager;

public class HowToPlayController {

    private final ViewManager viewManager;

    public HowToPlayController(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void setMainMenuView() {
        viewManager.set(new MainMenuView(viewManager));
    }
}
