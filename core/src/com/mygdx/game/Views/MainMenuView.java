package com.mygdx.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Controllers.MainMenuController;
import com.mygdx.game.Models.Button;
import com.mygdx.game.Models.GameObject;

public class MainMenuView extends View {

    private Button howToPlay;
    private Button newGame;
    private Button joinGame;
    private Button soundButton;
    private Button musicButton;
    private GameObject logo;
    private MainMenuController controller;
    private boolean isMusic = true;
    private boolean isSound = true;

    public MainMenuView(ViewManager vm) {
        super();
        this.controller = new MainMenuController(vm);
        howToPlay = new Button(new Texture(Gdx.files.internal("button_howtoplay.png")), 0.08, 0.88, 3,false ,false);
        newGame = new Button(new Texture(Gdx.files.internal("button_newgame.png")), 0.08, 0.08, 3,false,false);
        joinGame = new Button(new Texture(Gdx.files.internal("button_join.png")), 0.92, 0.08, 3,false, false);
        logo = new GameObject(new Texture(Gdx.files.internal("logo.png")), 1, 0.3, 3.5,false,true);
        soundButton = new Button(new Texture(Gdx.files.internal("sound_on.png")), 0.9, 0.88, 1,false ,false);
        musicButton = new Button(new Texture(Gdx.files.internal("music_on.png")), 0.85, 0.88, 1,false ,false);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            if (this.joinGame.isObjectClicked()) {
                dispose();
                controller.setJoinGameView();
            }
            if (this.howToPlay.isObjectClicked()) {
                setError("hello");
                //dispose();
                //controller.setHowToPlayView();
            }
            if (this.newGame.isObjectClicked()) {
                dispose();
                controller.setCreateGameView();
            }
            if (this.musicButton.isObjectClicked()){
                this.isMusic=!this.isMusic;
                if (this.isMusic){
                    this.musicButton.setFilePath("music_on.png");
                }
                else {
                    this.musicButton.setFilePath("music_off.png");
                }
            }
            if(this.soundButton.isObjectClicked()){
                this.isSound=!this.isSound;
                if (this.isSound){
                    this.soundButton.setFilePath("sound_on.png");
                }
                else {
                    this.soundButton.setFilePath("sound_off.png");
                }
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
        soundButton.drawGameObject(sb);
        musicButton.drawGameObject(sb);
        sb.end();
        super.renderStage();
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
