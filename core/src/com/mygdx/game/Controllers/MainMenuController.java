package com.mygdx.game.Controllers;

import com.mygdx.game.Views.CreateGameView;
import com.mygdx.game.Views.HowToPlayView;
import com.mygdx.game.Views.JoinGameView;
import com.mygdx.game.Views.ViewManager;

public class MainMenuController{

    private ViewManager viewManager;

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
