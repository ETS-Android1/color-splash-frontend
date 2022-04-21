package com.mygdx.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Views.GameObjects.Button;
import com.mygdx.game.controllers.GameLobbyController;


public class GameLobbyView extends View {

    private Button cancelButton;
    private Button startButton;
    private BitmapFont font;
    private GameLobbyController controller;

    protected GameLobbyView(ViewManager vm) {
        super(vm);
        controller = new GameLobbyController();

        controller.gameCreated();


        startButton = new Button(new Texture("button_start.png"), 0.92, 0.08, 3,false, false);
        cancelButton = new Button(new Texture("button_cancel.png"), 0.08, 0.08, 3,false,false);
        font = new BitmapFont(Gdx.files.internal("bebaskai.fnt"));
        font.setColor(Color.WHITE);
    }



    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            if (this.cancelButton.isObjectClicked()) {
                dispose();
                vm.set(new MainMenuView(vm));
            }
            if (this.startButton.isObjectClicked()) {
                dispose();
                vm.set(new SplashView(vm));

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
        cancelButton.drawGameObject(sb);
        if (controller.isHost){
            startButton.drawGameObject(sb);
        }
        font.getData().setScale((float)1.5);
        this.drawPlayers(sb);
        try {
            font.draw(sb, "Game pin:", (float) controller.gameInfo.players.get(0).avatar.getXPos() + 150, (float) controller.gameInfo.players.get(0).avatar.getYPos() + 600);
            font.getData().setScale(3);
            font.draw(sb,String.valueOf(this.controller.gameInfo.gameId), (float) controller.gameInfo.players.get(0).avatar.getXPos()+110, (float) controller.gameInfo.players.get(0).avatar.getYPos()+450);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sb.end();
    }

    @Override
    public void dispose() {
        cancelButton.getImage().dispose();
        startButton.getImage().dispose();
    }

    private void drawPlayers(SpriteBatch sb) {
        try {
            for (int i = 0; i < controller.gameInfo.players.size(); i++) {


                font.draw(sb, this.controller.gameInfo.players.get(i).name, (float) this.controller.gameInfo.players.get(i).avatar.getXPos() + 250, (float) this.controller.gameInfo.players.get(i).avatar.getYPos() + 130);
                this.controller.gameInfo.players.get(i).avatar.drawGameObject(sb);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
