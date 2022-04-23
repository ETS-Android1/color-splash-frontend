package com.mygdx.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ColorSplash;
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
    private boolean isSound = true;

    public MainMenuView(ViewManager vm) {
        super();
        this.controller = new MainMenuController(vm);
        howToPlay = new Button(new Texture(Gdx.files.internal("button_howtoplay.png")), 0.08, 0.88, 3,false ,false, vm);
        newGame = new Button(new Texture(Gdx.files.internal("button_newgame.png")), 0.08, 0.08, 3,false,false, vm);
        joinGame = new Button(new Texture(Gdx.files.internal("button_join.png")), 0.92, 0.08, 3,false, false, vm);
        logo = new GameObject(new Texture(Gdx.files.internal("logo.png")), 1, 0.3, 3.5,false,true);
        soundButton = new Button(new Texture(Gdx.files.internal("sound_on.png")), 0.9, 0.88, 3,false ,false, vm);
        musicButton = new Button(new Texture(Gdx.files.internal("music_on.png")), 0.75, 0.88, 3,false ,false, vm);
        if(!this.controller.isPlaying()){
            musicButton.setFilePath("music_off.png");
        }
        if(!this.controller.isSound()){
            soundButton.setFilePath("sound_off.png");
        }
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            if (this.joinGame.isObjectClicked()) {
                dispose();
                controller.setJoinGameView();
            }
            if (this.howToPlay.isObjectClicked()) {
                dispose();
                controller.setHowToPlayView();
            }
            if (this.newGame.isObjectClicked()) {
                dispose();
                controller.setCreateGameView();
            }
            if (this.musicButton.isObjectClicked()){
                if (this.controller.isPlaying()){
                    this.musicButton.setFilePath("music_off.png");
                    this.controller.setMusic();
                }
                else {
                    this.musicButton.setFilePath("music_on.png");
                    this.controller.setMusic();
                }
            }
            if(this.soundButton.isObjectClicked()){
                if (this.controller.isSound()){
                    this.soundButton.setFilePath("sound_off.png");
                    this.controller.setSound();
                }
                else {
                    this.soundButton.setFilePath("sound_on.png");
                    this.controller.setSound();
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
