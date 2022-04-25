package com.mygdx.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.views.gameObjects.Button;
import com.mygdx.game.views.gameObjects.GameObject;
import com.mygdx.game.views.gameObjects.InputField;
import com.mygdx.game.controllers.JoinGameController;

public class JoinGameView extends View{

    private final Button joinGame;
    private final InputField gamePin;
    private final InputField nickname;
    private final Button cancelButton;
    private final GameObject timerBackground;
    private final JoinGameController controller;

    public JoinGameView(ViewManager vm) {
        super();
        controller = new JoinGameController(vm);
        cancelButton = new Button(new Texture("button_cancel.png"), 0.08, 0.08, 3,false,false, vm);
        joinGame = new Button(new Texture(Gdx.files.internal("button_join.png")), 0.92, 0.08, 3,false, false, vm);
        gamePin = new InputField("Game Pin", new Texture(Gdx.files.internal("textfield_light.png")), 0.5,0.61,2,false,false);
        nickname = new InputField("Nickname", new Texture(Gdx.files.internal("textfield_light.png")), 0.5,0.51,2,false,false);
        timerBackground = new GameObject(new Texture(Gdx.files.internal("splash_orange.png")),1,0.15,5.3,false,true);
        gamePin.getTextField().setMaxLength(5);
        nickname.getTextField().setMaxLength(12);
        stage.addActor(gamePin.getTextField());
        stage.addActor(nickname.getTextField());
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            if (this.joinGame.isObjectClicked()) {
                String error = this.controller.validateInput(this.gamePin.getTextField().getText(), this.nickname.getTextField().getText());
                if (!error.equals("")) {
                    setError(error);
                }
            }
            else if (this.cancelButton.isObjectClicked()) {
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
        timerBackground.drawGameObject(sb);
        joinGame.drawGameObject(sb);
        cancelButton.drawGameObject(sb);
        sb.end();
        gamePin.drawStage(gamePin.getTextField());
        nickname.drawStage(nickname.getTextField());
        super.renderStage();
    }

    @Override
    public void dispose() {
        super.dispose();
        timerBackground.getImage().dispose();
        cancelButton.getImage().dispose();
        joinGame.getImage().dispose();
        stage.clear();
        gamePin.getImage().dispose();
        nickname.getImage().dispose();
    }

}
