package com.mygdx.game.Controllers;

import com.google.gson.Gson;
import com.mygdx.game.ColorSplash;
import com.mygdx.game.Events.EventsConstants;
import com.mygdx.game.Views.GetReadyView;
import com.mygdx.game.Views.ScoreBoardView;
import com.mygdx.game.Views.ViewManager;
import com.mygdx.game.dataClasses.DisplayColors;
import com.mygdx.game.dataClasses.ScoreBoardInfo;

import java.util.List;

import io.socket.emitter.Emitter;

public class AnswerController{
    private DisplayColors colorInfo;
    private boolean isLoading;
    private ScoreBoardInfo scoreBoardInfo;
    private boolean isHost;
    private ViewManager viewManager;

    public AnswerController(ViewManager viewManager, DisplayColors colorInfo) {
        this.viewManager = viewManager;
        this.isLoading = true;
        this.colorInfo = colorInfo;
        this.roundFinished();
    }

    public void playerFinished(int gameId, List<Integer> answer) {
        System.out.println("SENT");
        ColorSplash.socketManager.playerFinished(gameId, answer);
    }

    public void setScoreBoardView() {
        while (scoreBoardInfo == null) {
            //System.out.println("loading");
        }
        System.out.println("not loading");
        System.out.println(scoreBoardInfo);
        viewManager.set(new ScoreBoardView(viewManager, scoreBoardInfo));
    }

    public DisplayColors getColorInfo() {
        return colorInfo;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void roundFinished() {
        ColorSplash.socketManager.createListener(EventsConstants.endRound, scoreBoardListener());
    }

    public Emitter.Listener scoreBoardListener() {
        return new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                isLoading = true;
                Gson gson = new Gson();
                scoreBoardInfo = gson.fromJson(args[0].toString(), ScoreBoardInfo.class);
                isHost = scoreBoardInfo.hostId.equals(ColorSplash.socketManager.getSocketId());
                isLoading = false;
            }
        };
    }
}
