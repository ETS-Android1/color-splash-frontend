package com.mygdx.game.controllers;

import com.google.gson.Gson;
import com.mygdx.game.ColorSplash;
import com.mygdx.game.EventsConstants;
import com.mygdx.game.views.AnswerView;
import com.mygdx.game.views.ViewManager;
import com.mygdx.game.models.DisplayColors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.socket.emitter.Emitter;

public class SplashController {

    private DisplayColors colorInfo;
    private final ViewManager viewManager;
    private float splashTimer = 0;
    private float colorTimer = 0;
    private int colorCounter = 0;
    private int frameCounter = 0;
    private final List<Integer> colorList;

    public SplashController(ViewManager viewManager, DisplayColors colorInfo) {
        this.viewManager = viewManager;
        this.colorInfo = colorInfo;
        this.colorList = colorInfo.getNumber();
        this.displayColors();
        this.getColors();
    }

    public void addToTimers(float dt) {
        this.splashTimer += dt;
        this.colorTimer += dt;
        if (this.getColorTimer()>0.5 && this.getColorCounter()==(this.getColorList().size()-1)){
            displayFinished(getColorInfo().getGameId());
        }
    }

    public int changeDotColor() {
        if (this.getColorTimer()>0.9 && this.getColorCounter()<(this.getColorList().size()-1)){
            this.incrementColorCounter();
            this.resetColorTimer();
            this.resetFrameCounter();
            return this.getColorCounter();
        }
        return -1;
    }

    public float getColorTimer() {
        return colorTimer;
    }
    public void incrementFrameCounter() {
        frameCounter++;
    }

    public int getFrameCounter() {
        return frameCounter;
    }

    public boolean playSound(float dt) {
        if (this.getColorTimer()==0 && isSound()){
            this.addToTimers(dt);
            return true;
        }
        this.addToTimers(dt);
        return false;
    }

    public void resetFrameCounter() {
        this.frameCounter = 0;
    }

    public void incrementColorCounter() {
        colorCounter++;
    }

    public int getColorCounter() {
        return colorCounter;
    }

    public float getSplashTimer() {
        return splashTimer;
    }

    public void resetSplashTimer() {
        this.splashTimer = 0;
    }

    public void resetColorTimer() {
        this.colorTimer = 0;
    }

    public void getColors() {
        ColorSplash.socketManager.createListener(EventsConstants.roundStarted, roundStartedListener());
    }

    public Emitter.Listener roundStartedListener() {
        return new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Gson gson = new Gson();
                colorInfo = gson.fromJson(args[0].toString(), DisplayColors.class);
            }
        };
    }

    public void displayColors() {
        ColorSplash.socketManager.createListener(EventsConstants.displayColors, colorListener());
    }

    public Emitter.Listener colorListener() {
        return new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Gson gson = new Gson();
                colorInfo = gson.fromJson(args[0].toString(), DisplayColors.class);
            }
        };
    }

    public void displayFinished(int gameId) {
        ColorSplash.socketManager.colorsDisplayFinished(gameId);
        setAnswerView();
    }

    private void setAnswerView() {
        viewManager.set(new AnswerView(viewManager, colorInfo));
    }

    public DisplayColors getColorInfo() {
        return colorInfo;
    }

    public boolean isSound(){return viewManager.isSound();}

    public List<Integer> getColorList() {
        return colorList;
    }

    public List<Integer> updateSplash() {
        if (this.getSplashTimer()>0.05 && this.getFrameCounter()<4){
            this.incrementFrameCounter();
            this.resetSplashTimer();
            return new ArrayList<>(Arrays.asList(this.getColorList().get(this.getColorCounter()), (Integer) getFrameCounter()));
        }
        return new ArrayList<>();
    }
}
