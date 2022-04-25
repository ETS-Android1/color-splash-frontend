package com.mygdx.game.Controllers;

import com.mygdx.game.Views.SplashView;
import com.mygdx.game.Views.ViewManager;
import com.mygdx.game.dataClasses.DisplayColors;

public class GetReadyController {

    private final ViewManager viewManager;
    private final DisplayColors colorInfo;
    private float timer = 0;

    public GetReadyController(ViewManager viewManager, DisplayColors colorInfo) {
        this.viewManager = viewManager;
        this.colorInfo = colorInfo;
    }

    public void setSplashView() {
        viewManager.set(new SplashView(viewManager, colorInfo));
    }

    public float getTimer() {
        return timer;
    }

    public void addToTimer(float dt) {
        this.timer += dt;
        if(this.getTimer()>=2){
            setSplashView();
        }
    }
}
