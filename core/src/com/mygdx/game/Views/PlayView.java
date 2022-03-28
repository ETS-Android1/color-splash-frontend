package com.mygdx.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ColorSplash;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.Views.GameObjects.Background;

public class PlayView extends View{

    private Texture splash;
    private Background background;
    private Hud hud;

    private Integer worldTimer;
    private float timeCount;


    protected PlayView(ViewManager vm) {
        super(vm);
        splash = new Texture(Gdx.files.internal("splash.png"));
        worldTimer = 60;
        timeCount = 0;
        hud = new Hud();
        background = new Background(new Texture(Gdx.files.internal("background_grey.png")), ColorSplash.V_WIDTH, ColorSplash.V_HEIGHT);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()) {
            vm.set(new MainMenuView(vm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        hud.updateTimer(dt);
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(splash, 0, 0);
        hud.draw(sb, 100, 100);
        sb.end();
    }

    @Override
    public void dispose() {
        splash.dispose();
        hud.dispose();
    }

}
