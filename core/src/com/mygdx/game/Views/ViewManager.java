package com.mygdx.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class ViewManager {

    private final Stack<View> views;
    private final Music music;
    private boolean isPLaying = true;
    private boolean isSound = true;

    public ViewManager() {
        views = new Stack<>();
        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        music.setLooping(true);
        music.play();
    }

    public void push(View state) {
        views.push(state);
    }

    public View peek() { return views.peek(); }

    public void set(View state) {
        views.peek().dispose();
        views.pop();
        views.push(state);
    }

    public Stack<View> getViews() {
        return this.views;
    }

    public void update(float dt) {
        views.peek().update(dt);
    }

    public void render(SpriteBatch sb) {
        views.peek().render(sb);
    }

    public void setMusic() {
        this.isPLaying=!this.isPLaying;
        if (isPLaying){
            music.play();
        }
        else{
            music.pause();
        }
    }
    public boolean isPLaying(){return this.isPLaying;}

    public boolean isSound(){return this.isSound;}

    public void setSound(){this.isSound=!this.isSound;}
}
