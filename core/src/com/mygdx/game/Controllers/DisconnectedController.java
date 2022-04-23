package com.mygdx.game.Controllers;

import com.mygdx.game.Views.MainMenuView;
import com.mygdx.game.Views.ViewManager;

public class DisconnectedController{

    private ViewManager viewManager;

    public DisconnectedController(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void setMainMenuView() {
        viewManager.set(new MainMenuView(viewManager));
    }
}
