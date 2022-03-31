package com.mygdx.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Views.GameObjects.Button;
import com.mygdx.game.Views.GameObjects.GameObject;
import com.mygdx.game.Views.Listeners.GamePinListener;
import com.mygdx.game.Views.Listeners.UserNameListener;

public class MainMenuView extends View {

    private Button howToPlay;
    private Button newGame;
    private Button joinGame;
    private GameObject logo;
    private GamePinListener gamePinListener = new GamePinListener();

    //private InputField gamePinField;

    //private Stage stage;
    //private Viewport viewport = new StretchViewport();


    public MainMenuView(ViewManager vm) {
        super(vm);
        howToPlay = new Button(new Texture(Gdx.files.internal("button_howtoplay.png")), 0.08, 0.88, 3,false ,false);
        newGame = new Button(new Texture(Gdx.files.internal("button_newgame.png")), 0.08, 0.08, 3,false,false);
        joinGame = new Button(new Texture(Gdx.files.internal("button_join.png")), 0.92, 0.08, 3,false, false);
        logo = new GameObject(new Texture(Gdx.files.internal("logo.png")), 1, 0.30, 1.9,false,true);


    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            if (this.joinGame.isObjectClicked()) {
                Gdx.input.getTextInput(gamePinListener, "Enter game pin:", "", "Pin");

                //draw(this.sb);

            }
            if (this.howToPlay.isObjectClicked()) {
                vm.push(new HowToPlayView(vm));
                //dispose();
            }
            if (this.newGame.isObjectClicked()) {
                vm.set(new CreateGameView(vm));
                dispose();
            }
        }
        /*
            if (joinGame.isObjectClicked()) {
                vm.set(new PlayView(vm));
                dispose();
            }
        }*/
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        logo.drawGameObject(sb);
        howToPlay.drawGameObject(sb);
        newGame.drawGameObject(sb);
        joinGame.drawGameObject(sb);
        //gamePinField.drawGameObject(sb);
        sb.end();
        //Table table = new Table();
        //table.add(gamePinField);
    }

    @Override
    public void dispose() {
        background.getImage().dispose();
        howToPlay.getImage().dispose();
        newGame.getImage().dispose();
        joinGame.getImage().dispose();
        logo.getImage().dispose();
    }


}
