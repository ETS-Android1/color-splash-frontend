package com.mygdx.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.ColorSplash;
import com.mygdx.game.Views.GameObjects.Background;

public abstract class View {

    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected ViewManager vm;
    protected SpriteBatch sb;
    protected Background background;

    protected View(ViewManager vm) {
        this.vm = vm;
        cam = new OrthographicCamera();
        mouse = new Vector3();
        this.sb = new SpriteBatch();
        background = new Background(new Texture(Gdx.files.internal("background_grey.png")),0,0, 5,true ,true);

    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    public void render(SpriteBatch sb) {
        sb.begin();
        background.drawGameObject(sb);
    }
    public abstract void dispose();



}
