package com.mygdx.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Models.Button;
import com.mygdx.game.Models.GameObject;

public class MainMenuView extends View {

    private Button howToPlay;
    private Button newGame;
    private Button joinGame;
    private GameObject logo;
    private Stage stage;

    public MainMenuView(ViewManager vm) {
        super(vm);

        howToPlay = new Button(new Texture(Gdx.files.internal("button_howtoplay.png")), 0.08, 0.88, 3,false ,false);
        newGame = new Button(new Texture(Gdx.files.internal("button_newgame.png")), 0.08, 0.1, 3,false,false);
        joinGame = new Button(new Texture(Gdx.files.internal("button_join.png")), 0.92, 0.1, 3,false, false);
        logo = new GameObject(new Texture(Gdx.files.internal("logo.png")), 1, 0.3, 3.5,false,true);
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            if (this.joinGame.isObjectClicked()) {
                dispose();
                vm.set(new JoinGameView(vm));

                //draw(this.sb);

            }
            if (this.howToPlay.isObjectClicked()) {
                dispose();
                //vm.set(new HowToPlayView(vm));
                vm.set(new AnswerView(vm));
                //dispose();
            }
            if (this.newGame.isObjectClicked()) {
                dispose();
                vm.set(new CreateGameView(vm));
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
        howToPlay.drawGameObject(sb);
        newGame.drawGameObject(sb);
        logo.drawGameObject(sb);
        joinGame.drawGameObject(sb);
        sb.end();
        stage.draw();
        stage.act();
    }

    @Override
    public void dispose() {
        background.getImage().dispose();
        howToPlay.getImage().dispose();
        newGame.getImage().dispose();
        joinGame.getImage().dispose();
        logo.getImage().dispose();
        stage.clear();
    }


}
