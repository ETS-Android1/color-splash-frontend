package com.mygdx.game.Controllers;

import com.mygdx.game.Views.SplashView;
import com.mygdx.game.Views.ViewManager;

public class LoadingController extends ErrorController {

    public LoadingController(ViewManager viewManager) {
        super(viewManager);
    }

    public void setSplashView() {
        //viewManager.set(new SplashView(viewManager));
    }
}
