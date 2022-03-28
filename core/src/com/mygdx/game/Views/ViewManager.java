package com.mygdx.game.Views;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class ViewManager {

    private Stack<View> views;

    public ViewManager() {
        views = new Stack<>();
    }

    public void push(View state) {
        views.push(state);
    }

    public void pop() {
        views.pop();
    }

    public void set(View state) {
        views.pop();
        views.push(state);
    }

    public void update(float dt) {
        views.peek().update(dt);
    }

    public void render(SpriteBatch sp) {
        views.peek().render(sp);
    }
}
