package com.mygdx.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Views.GameObjects.Background;

public class HowToPlayView extends View{

    private Background background;

    protected HowToPlayView(ViewManager vm) {
        super(vm);
        System.out.println("HELLO VIEW");
        background = new Background(new Texture(Gdx.files.internal("background_grey.png")), 1, 1, 1,false ,false);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        background.drawGameObject(sb);
        sb.end();
    }

    @Override
    public void dispose() {
        background.getImage().dispose();

    }

}
