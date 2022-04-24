package com.mygdx.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Models.Button;
import com.mygdx.game.Models.ErrorDialog;
import com.mygdx.game.Models.GameObject;
import com.mygdx.game.Models.InputField;
import com.mygdx.game.Controllers.JoinGameController;

public class JoinGameView extends View{

    private Button joinGame;
    private InputField gamePin;
    private InputField nickname;
    private Button cancelButton;
    private GameObject timerBackground;
    private JoinGameController controller;

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
                if (this.gamePin.getTextField().getText()!="" && this.nickname.getTextField().getText()!="") {
                    try {
                        System.out.println("GameId: " + Integer.parseInt(this.gamePin.getTextField().getText()));
                        System.out.println("Nickname: " + this.nickname.getTextField().getText());
                        this.controller.joinGame(Integer.parseInt(this.gamePin.getTextField().getText()), this.nickname.getTextField().getText());
                    } catch (Exception e) {
                        setError("Not valid Game Pin");
                    }
                } else if (this.gamePin.getTextField().getText() == "" && this.nickname.getTextField().getText() != "") {
                    setError("Fill in Game Pin");
                } else if (this.gamePin.getTextField().getText() != "" && this.nickname.getTextField().getText() == "") {
                    try {
                        Double.parseDouble(this.gamePin.getTextField().getText());
                        setError("Fill in nickname");
                    } catch (NumberFormatException e) {
                        setError("Fill in nickname and \n   valid Game Pin");
                    }
                } else if (this.gamePin.getTextField().getText() == "" && this.nickname.getTextField().getText() == "") {
                    setError("Fill in Game Pin and nickname");
                }
            }
            else if (this.cancelButton.isObjectClicked()) {
                dispose();
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
        background.getImage().dispose();
        timerBackground.getImage().dispose();
        cancelButton.getImage().dispose();
        joinGame.getImage().dispose();
        stage.clear();
        gamePin.getImage().dispose();
        nickname.getImage().dispose();
    }

}
