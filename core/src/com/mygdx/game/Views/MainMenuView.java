package com.mygdx.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ColorSplash;
import com.mygdx.game.Models.Background;
import com.mygdx.game.Models.GameObject;

public class MainMenuView extends View {

    private Background background;
    private GameObject playButton;

    public MainMenuView(ViewManager vm) {
        super(vm);
        Texture button = new Texture(Gdx.files.internal("start_button.png"));
        playButton = new GameObject(button, (ColorSplash.V_WIDTH/2) - (button.getWidth()/2), ColorSplash.V_HEIGHT/2);
        background = new Background(new Texture(Gdx.files.internal("background.png")), ColorSplash.V_WIDTH, ColorSplash.V_HEIGHT);
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()) {
            if (playButton.isObjectClicked()) {
                vm.set(new PlayView(vm));
                dispose();
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        background.drawBackground(sb);
        playButton.drawGameObject(sb);
        sb.end();
    }

    @Override
    public void dispose() {
        background.getImage().dispose();
        playButton.getImage().dispose();
    }
}
