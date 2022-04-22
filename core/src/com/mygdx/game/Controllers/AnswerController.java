package com.mygdx.game.Controllers;

import com.google.gson.Gson;
import com.mygdx.game.ColorSplash;
import com.mygdx.game.Events.EventsConstants;
import com.mygdx.game.Views.ScoreBoardView;
import com.mygdx.game.Views.ViewManager;
import com.mygdx.game.dataClasses.DisplayColors;

import java.util.List;

import io.socket.emitter.Emitter;

public class AnswerController {
    private ViewManager viewManager;
    private DisplayColors colorInfo;
    private boolean isLoading = true;

    public AnswerController(ViewManager viewManager) {
        this.getColors();
        this.viewManager = viewManager;
    }

    public void getColors() {
        ColorSplash.socketManager.createListener(EventsConstants.roundStarted, colorListener());
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

    public void playerFinished(int gameId, List<Integer> answer) {
        ColorSplash.socketManager.playerFinished(gameId, answer);
    }

    public void setScoreBoardView() {
        viewManager.set(new ScoreBoardView(viewManager));
    }

    public DisplayColors getColorInfo() {
        return colorInfo;
    }

    public boolean isLoading() {
        return isLoading;
    }
}
