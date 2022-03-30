package com.mygdx.game.Views;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.ColorSplash;

public abstract class View {

    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected ViewManager vm;
    protected SpriteBatch sb;

    protected View(ViewManager vm) {
        this.vm = vm;
        cam = new OrthographicCamera();
        mouse = new Vector3();
        this.sb = new SpriteBatch();

    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();



}
