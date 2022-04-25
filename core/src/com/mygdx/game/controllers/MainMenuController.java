package com.mygdx.game.controllers;

import com.mygdx.game.views.CreateGameView;
import com.mygdx.game.views.HowToPlayView;
import com.mygdx.game.views.JoinGameView;
import com.mygdx.game.ViewManager;

public class MainMenuController{

    private final ViewManager viewManager;

    public MainMenuController(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void setJoinGameView() {
        viewManager.set(new JoinGameView(viewManager));
    }

    public void setHowToPlayView() { viewManager.set(new HowToPlayView(viewManager)); }

    public void setCreateGameView() {
        viewManager.set(new CreateGameView(viewManager));
    }

    public void setMusic(){
        viewManager.setMusic();
    }

    public boolean isPlaying(){return viewManager.isPLaying();}

    public void setSound(){
        viewManager.setSound();
    }

    public boolean isSound(){return viewManager.isSound();}
}
