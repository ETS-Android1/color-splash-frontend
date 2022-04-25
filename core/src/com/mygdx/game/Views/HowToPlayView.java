package com.mygdx.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Controllers.HowToPlayController;
import com.mygdx.game.Models.Background;
import com.mygdx.game.Models.Button;

public class HowToPlayView extends View{

    private Background background;
    private Button exitButton;
    private HowToPlayController controller;

    public HowToPlayView(ViewManager vm) {
        super();
        this.controller = new HowToPlayController(vm);
        background = new Background(new Texture(Gdx.files.internal("HowToPlayScreen.png")), 1, 1, 2.8,true ,true);
        exitButton = new Button(new Texture(Gdx.files.internal("button_exit.png")),0.92, 0.08, 3,false, false, vm);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            if (this.exitButton.isObjectClicked()) {
                controller.setMainMenuView();
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        background.drawGameObject(sb);
        exitButton.drawGameObject(sb);
        sb.end();
        super.renderStage();
    }

    @Override
    public void dispose() {
        super.dispose();
        background.getImage().dispose();
        exitButton.getImage().dispose();
    }

}
