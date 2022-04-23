package com.mygdx.game.Controllers;

import com.mygdx.game.Views.MainMenuView;
import com.mygdx.game.Views.ViewManager;

public class DisconnectedController extends ErrorController{

    public DisconnectedController(ViewManager viewManager) {
        super(viewManager);
    }

    public void setMainMenuView() {
        viewManager.set(new MainMenuView(viewManager));
    }
}
