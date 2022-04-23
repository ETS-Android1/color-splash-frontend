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

    private DisplayColors colorInfo;
    private boolean isLoading = true;
    private ViewManager viewManager;

    public SplashController(ViewManager viewManager, DisplayColors colorInfo) {
        this.viewManager = viewManager;
        this.colorInfo = colorInfo;
        this.displayColors();
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

    public void displayFinished(int gameId) {
        ColorSplash.socketManager.colorsDisplayFinished(gameId);
    }

    public void setAnswerView() {
        viewManager.set(new AnswerView(viewManager));
    }

    public DisplayColors getColorInfo() {
        return colorInfo;
    }

    public boolean isLoading() {
        return isLoading;
    }
}
