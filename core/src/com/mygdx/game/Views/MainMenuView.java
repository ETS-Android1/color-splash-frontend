package com.mygdx.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.ColorSplash;
import com.mygdx.game.Views.GameObjects.Background;
import com.mygdx.game.Views.GameObjects.Button;
import com.mygdx.game.Views.GameObjects.GameObject;
import com.mygdx.game.Views.GameObjects.InputField;

import org.graalvm.compiler.replacements.Log;

public class MainMenuView extends View {

    private Background background;
    private Button howToPlay;
    private Button newGame;
    private Button joinGame;
    private GameObject logo;
    GamePinListener listener = new GamePinListener();

    //private InputField gamePinField;

    //private Stage stage;
    //private Viewport viewport = new StretchViewport();


    public MainMenuView(ViewManager vm) {
        super(vm);
        background = new Background(new Texture(Gdx.files.internal("background_grey.png")), 1, 1, 1,false ,false);
        howToPlay = new Button(new Texture(Gdx.files.internal("button_howtoplay.png")), 0.08, 0.88, 3,false ,false);
        newGame = new Button(new Texture(Gdx.files.internal("button_newgame.png")), 0.08, 0.08, 3,false,false);
        joinGame = new Button(new Texture(Gdx.files.internal("button_join.png")), 0.92, 0.08, 3,false, false);
        logo = new GameObject(new Texture(Gdx.files.internal("logo.png")), 1, 0.30, 1.9,false,true);






    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            if (this.joinGame.isObjectClicked()) {
                Gdx.input.getTextInput(listener, "Enter game pin:", "", "Pin");
                Gdx.input.getTextInput(listener, "Enter your name:", "", "Name");

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
        sb.begin();
        background.drawBackground(sb);
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
