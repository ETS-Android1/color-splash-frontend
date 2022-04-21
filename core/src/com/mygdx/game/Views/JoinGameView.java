package com.mygdx.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Views.GameObjects.Button;
import com.mygdx.game.Views.GameObjects.GameObject;
import com.mygdx.game.Views.GameObjects.InputField;

public class JoinGameView extends View{

    private Button joinGame;
    private Stage stage;
    private InputField gamePin;
    private InputField nickname;
    private Button cancelButton;
    private GameObject timerBackground;

    public JoinGameView(ViewManager vm) {
        super(vm);

        cancelButton = new Button(new Texture("button_cancel.png"), 0.08, 0.1, 3,false,false);
        joinGame = new Button(new Texture(Gdx.files.internal("button_join.png")), 0.92, 0.1, 3,false, false);
        gamePin = new InputField("Game Pin", new Texture(Gdx.files.internal("textfield_light.png")), 0.5,0.61,2,false,false);
        nickname = new InputField("Nickname", new Texture(Gdx.files.internal("textfield_light.png")), 0.5,0.51,2,false,false);
        timerBackground = new GameObject(new Texture(Gdx.files.internal("splash_orange.png")),1,0.15,5.3,false,true);
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);
        stage.addActor(gamePin.getTextField());
        stage.addActor(nickname.getTextField());

    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            if (this.joinGame.isObjectClicked()) {
                dispose();
                vm.set(new GameLobbyView(vm));
            }
            if (this.cancelButton.isObjectClicked()) {
                dispose();
                vm.set(new MainMenuView(vm));
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
        stage.draw();
        stage.act();
    }

    @Override
    public void dispose() {
        background.getImage().dispose();
        timerBackground.getImage().dispose();
        cancelButton.getImage().dispose();
        joinGame.getImage().dispose();
        stage.clear();
    }

}
