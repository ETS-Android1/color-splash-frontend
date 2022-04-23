package com.mygdx.game.Controllers;

import com.mygdx.game.Views.SplashView;
import com.mygdx.game.Views.ViewManager;
import com.mygdx.game.dataClasses.DisplayColors;

public class GetReadyController {

    private ViewManager viewManager;

    public GetReadyController(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void setSplashView(DisplayColors colorInfo) {
        viewManager.set(new SplashView(viewManager, colorInfo));

    }
}
