package com.mygdx.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.mygdx.game.Views.GameObjects.Button;
import com.mygdx.game.Views.GameObjects.GameObject;
import com.mygdx.game.Views.Listeners.GamePinListener;
import com.mygdx.game.Views.Listeners.UserNameListener;

public class MainMenuView extends View {

    private Button howToPlay;
    private Button newGame;
    private Button joinGame;
    //private GameObject logo;
    private GamePinListener gamePinListener = new GamePinListener();
    //private TextField usernameTextField;

    //private InputField gamePinField;

    //private Stage stage;
    //private Viewport viewport = new StretchViewport();


    public MainMenuView(ViewManager vm) {
        super(vm);
        howToPlay = new Button(new Texture(Gdx.files.internal("button_howtoplay.png")), 0.08, 0.88, 3,false ,false);
        newGame = new Button(new Texture(Gdx.files.internal("button_newgame.png")), 0.08, 0.08, 3,false,false);
        joinGame = new Button(new Texture(Gdx.files.internal("button_join.png")), 0.92, 0.08, 3,false, false);
        /*Skin skin = new Skin(Gdx.files.internal("skin.json"));
        usernameTextField = new TextField("", skin);
        usernameTextField.setPosition(24,73);
        usernameTextField.setSize(88, 14);

         */



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
        //logo.drawGameObject(sb);
        howToPlay.drawGameObject(sb);
        newGame.drawGameObject(sb);
        joinGame.drawGameObject(sb);
        //gamePinField.drawGameObject(sb);
        //usernameTextField.draw(sb, 2);
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
        //logo.getImage().dispose();
    }


}
