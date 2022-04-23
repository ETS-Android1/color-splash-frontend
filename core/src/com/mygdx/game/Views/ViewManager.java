package com.mygdx.game.Views;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class ViewManager {

    private Stack<View> views;
    private Music music;

    public ViewManager() {
        views = new Stack<>();
        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        music.setLooping(true);
        music.play();
    }

    public void push(View state) {
        views.push(state);
    }

    public void pop() {
        views.pop();
    }

    public View peek() { return views.peek(); }

    public void set(View state) {
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

    public void setMusic(boolean play) {
        if (play){
            music.play();
        }
        else{
            music.pause();
        }
    }
}
