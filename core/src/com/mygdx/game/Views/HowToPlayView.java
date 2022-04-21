package com.mygdx.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Views.GameObjects.Background;
import com.mygdx.game.Views.GameObjects.Button;

public class HowToPlayView extends View{

    private Background background;
    private Button exitButton;

    protected HowToPlayView(ViewManager vm) {
        super(vm);
        background = new Background(new Texture(Gdx.files.internal("HowToPlayScreen.png")), 1, 1, 2.8,true ,true);
        exitButton = new Button(new Texture(Gdx.files.internal("button_exit.png")),0.92, 0.02, 3,false, false);
    }

    @Override
    public void handleInput() {
        //|| Gdx.input.isKeyPressed(Input.Keys.BACK)
        if (Gdx.input.justTouched()) {
            if (this.exitButton.isObjectClicked()) {
                dispose();
                vm.set(new MainMenuView(vm));
                //draw(this.sb);

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
        background.drawGameObject(sb);
        exitButton.drawGameObject(sb);
        sb.end();
    }

    @Override
    public void dispose() {
        background.getImage().dispose();
        exitButton.getImage().dispose();

    }

}
