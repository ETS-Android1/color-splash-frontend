package com.mygdx.game.Views;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.ColorSplash;

public abstract class View {

    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected ViewManager vm;

    protected View(ViewManager vm) {
        this.vm = vm;
        cam = new OrthographicCamera();
        //cam.setToOrtho(false,360,640);
        //cam.setToOrtho(true, ColorSplash.V_WIDTH, ColorSplash.V_HEIGHT);
        //cam.rotate(180);
        mouse = new Vector3();

    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sp);
    public abstract void dispose();



}
