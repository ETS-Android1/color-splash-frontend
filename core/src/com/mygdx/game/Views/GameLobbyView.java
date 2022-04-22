package com.mygdx.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Models.Button;
import com.mygdx.game.Models.GameObject;
import com.mygdx.game.Controllers.GameLobbyController;
import com.mygdx.game.dataClasses.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GameLobbyView extends View {

    private Button cancelButton;
    private Button startButton;
    private BitmapFont font;
    private GameLobbyController controller;

    private List<GameObject> avatars = new ArrayList<>();
    private List<String> avatarPics = new ArrayList<>(Arrays.asList("avatar_orange.png", "avatar_green.png", "avatar_pink.png", "avatar_purple.png"));


    protected GameLobbyView(ViewManager vm) {
        super(vm);
        controller = new GameLobbyController();

        controller.gameCreated();

        for (int i = 0; i < 4; i++) {
            avatars.add(new GameObject(new Texture(Gdx.files.internal("empty.png")), 0.2, 0.6 - 0.12 * i, 1, false, false));
        }

        boolean loading = true;

        while (loading) {
            loading = controller.isLoading;
            if (!loading) {
                for (Player player : controller.gameInfo.players) {
                    avatars.get(player.getAvatarIndex()).setFilePath(avatarPics.get(player.getAvatarIndex()));
                }
            }
        }

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
                this.controller.startGame(controller.gameInfo.gameId);
                dispose();
                vm.set(new GetReadyView(vm));

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

        font.draw(sb, "Game pin:", (float) avatars.get(0).getXPos() + 150, (float) avatars.get(0).getYPos() + 600);
        font.getData().setScale(3);
        font.draw(sb,String.valueOf(this.controller.gameInfo.gameId), (float) avatars.get(0).getXPos()+110, (float) avatars.get(0).getYPos()+450);

        sb.end();
    }

    @Override
    public void dispose() {
        cancelButton.getImage().dispose();
        startButton.getImage().dispose();
    }

    private void drawPlayers(SpriteBatch sb) {

        for (int i = 0; i < 4; i++) {
            try {
                for (Player player : controller.gameInfo.players) {
                    avatars.get(player.getAvatarIndex()).setFilePath(avatarPics.get(player.getAvatarIndex()));
                }
                font.draw(sb, this.controller.gameInfo.players.get(i).name, (float) avatars.get(i).getXPos() + 250, (float) avatars.get(i).getYPos() + 130);
            } catch (Exception e) {
                //e.printStackTrace();
            }
            avatars.get(i).drawGameObject(sb);
        }
    }
}
