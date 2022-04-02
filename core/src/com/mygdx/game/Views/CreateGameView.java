package com.mygdx.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Views.GameObjects.Button;
import com.mygdx.game.Views.Listeners.CreateGameListener;

public class CreateGameView extends View{

    private Button createGame;
    private CreateGameListener createGameListener = new CreateGameListener();

    protected CreateGameView(ViewManager vm) {
        super(vm);
        createGame = new Button(new Texture("button_create.png"), 1,0.3,3, false, false);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            if (this.createGame.isObjectClicked()) {
                Gdx.input.getTextInput(createGameListener, "Enter your name:", "", "Pin");
                System.out.println("kom seg hit, ja");
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
        super.render(sb);
        createGame.drawGameObject(sb);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
