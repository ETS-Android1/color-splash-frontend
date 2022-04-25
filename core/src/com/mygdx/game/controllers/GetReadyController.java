package com.mygdx.game.controllers;

import com.mygdx.game.views.SplashView;
import com.mygdx.game.views.ViewManager;
import com.mygdx.game.models.DisplayColors;

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
