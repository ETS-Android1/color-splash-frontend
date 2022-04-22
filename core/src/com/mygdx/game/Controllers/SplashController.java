package com.mygdx.game.Controllers;

import com.google.gson.Gson;
import com.mygdx.game.ColorSplash;
import com.mygdx.game.Events.EventsConstants;
import com.mygdx.game.Views.AnswerView;
import com.mygdx.game.Views.ViewManager;
import com.mygdx.game.dataClasses.DisplayColors;
import com.mygdx.game.dataClasses.GameInfo;

import io.socket.emitter.Emitter;

public class SplashController {
    private ViewManager viewManager;
    private DisplayColors colorInfo;
    private boolean isLoading = true;


    public SplashController(ViewManager viewManager) {
        this.displayColors();
        this.viewManager = viewManager;
    }

    public void displayColors() {
        ColorSplash.socketManager.createListener(EventsConstants.displayColors, colorListener());
    }

    public Emitter.Listener colorListener() {
        return new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                isLoading = true;
                Gson gson = new Gson();
                colorInfo = gson.fromJson(args[0].toString(), DisplayColors.class);
                isLoading = false;
            }
        };
    }

    public void setAnswerView() {
        viewManager.set(new AnswerView(viewManager));
    }
}
