package com.mygdx.game.Controllers;

import com.mygdx.game.Views.SplashView;
import com.mygdx.game.Views.ViewManager;

public class LoadingController {

    private ViewManager viewManager;

    public LoadingController(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void setSplashView() {
        //viewManager.set(new SplashView(viewManager));
    }
}
