package com.mygdx.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Controllers.MainMenuController;
import com.mygdx.game.Models.Button;
import com.mygdx.game.Models.GameObject;

public class MainMenuView extends View {

    private final Button howToPlay;
    private final Button newGame;
    private final Button joinGame;
    private final Button soundButton;
    private final Button musicButton;
    private final GameObject logo;
    private final MainMenuController controller;
    private final Texture musicOff = new Texture(Gdx.files.internal("music_off.png"));
    private final Texture soundOff = new Texture(Gdx.files.internal("sound_off.png"));
    private final Texture musicOn = new Texture(Gdx.files.internal("music_on.png"));
    private final Texture soundOn = new Texture(Gdx.files.internal("sound_on.png"));

    public MainMenuView(ViewManager vm) {
        super();
        this.controller = new MainMenuController(vm);
        howToPlay = new Button(new Texture(Gdx.files.internal("button_howtoplay.png")), 0.08, 0.88, 3,false ,false, vm);
        newGame = new Button(new Texture(Gdx.files.internal("button_newgame.png")), 0.08, 0.08, 3,false,false, vm);
        joinGame = new Button(new Texture(Gdx.files.internal("button_join.png")), 0.92, 0.08, 3,false, false, vm);
        logo = new GameObject(new Texture(Gdx.files.internal("logo.png")), 1, 0.3, 3.5,false,true);
        soundButton = new Button(soundOn, 0.9, 0.88, 3,false ,false, vm);
        musicButton = new Button(musicOn, 0.75, 0.88, 3,false ,false, vm);
        if(!this.controller.isPlaying()){
            musicButton.setImage(musicOff);
        }
        if(!this.controller.isSound()){
            soundButton.setImage(soundOff);
        }
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            if (this.joinGame.isObjectClicked()) {
                controller.setJoinGameView();
            }
            if (this.howToPlay.isObjectClicked()) {
                controller.setHowToPlayView();
            }
            if (this.newGame.isObjectClicked()) {
                controller.setCreateGameView();
            }
            if (this.musicButton.isObjectClicked()){
                if (this.controller.isPlaying()){
                    this.musicButton.setImage(musicOff);
                }
                else {
                    this.musicButton.setImage(musicOn);
                }
                this.controller.setMusic();
            }
            if(this.soundButton.isObjectClicked()){
                if (this.controller.isSound()){
                    this.soundButton.setImage(soundOff);
                }
                else {
                    this.soundButton.setImage(soundOn);
                }
                this.controller.setSound();
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
        super.dispose();
        howToPlay.getImage().dispose();
        newGame.getImage().dispose();
        joinGame.getImage().dispose();
        logo.getImage().dispose();
        stage.clear();
    }
}
