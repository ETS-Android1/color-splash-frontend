package com.mygdx.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Models.Button;
import com.mygdx.game.Models.GameObject;
import com.mygdx.game.Controllers.GameLobbyController;
import com.mygdx.game.dataClasses.GameInfo;
import com.mygdx.game.dataClasses.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GameLobbyView extends View {

    private Button cancelButton;
    private Button startButton;
    private BitmapFont font;
    private GameLobbyController controller;
    private String diffRounds;

    private List<GameObject> avatars = new ArrayList<>();
    private List<Texture> avatarPics = new ArrayList<>(
            Arrays.asList(
                    new Texture(Gdx.files.internal("avatar_orange.png")), 
                    new Texture(Gdx.files.internal("avatar_green.png")),
                    new Texture(Gdx.files.internal("avatar_pink.png")),
                    new Texture(Gdx.files.internal("avatar_purple.png")),
                    new Texture(Gdx.files.internal("empty.png"))
            )
    );

    public GameLobbyView(ViewManager vm, boolean isHost) {
        super();
        controller = new GameLobbyController(vm);
        System.out.println("game lobby created");

        for (int i = 0; i < 4; i++) {
            avatars.add(new GameObject(avatarPics.get(4), 0.2, 0.6 - 0.12 * i, 1, false, false));
        }

        if (isHost) {
            boolean loading = true;
            while (loading) {
                loading = controller.isLoading();
            }
            for (Player player : controller.getGameInfo().players) {
                avatars.get(player.getAvatarIndex()).setImage(avatarPics.get(player.getAvatarIndex()));
            }
            diffRounds = "Difficulty: "+controller.getGameInfo().difficulty+"    Rounds: "+controller.getGameInfo().rounds;
        }

        startButton = new Button(new Texture("button_start.png"), 0.92, 0.08, 3,false, false, vm);
        cancelButton = new Button(new Texture("button_cancel.png"), 0.08, 0.08, 3,false,false, vm);
        font = new BitmapFont(Gdx.files.internal("bebaskai.fnt"));

    }
    public GameLobbyView(ViewManager vm, boolean isHost, GameInfo gameInfo) {
        this(vm, isHost);
        this.controller.setGameInfo(gameInfo);
        for (Player player : this.controller.getGameInfo().players) {
            avatars.get(player.getAvatarIndex()).setImage(avatarPics.get(player.getAvatarIndex()));
        }
        diffRounds = "Difficulty: "+controller.getGameInfo().difficulty+"    Rounds: "+controller.getGameInfo().rounds;

    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            if (this.cancelButton.isObjectClicked()) {
                dispose();
                controller.leaveGame();
            }
            if (this.startButton.isObjectClicked() && this.controller.isHost()) {
                this.controller.startGame(controller.getGameInfo().gameId);
                boolean loading = true;
                while (loading){
                    loading = controller.isLoading();
                    if (!loading){
                        dispose();
                        controller.setGetReadyView();
                    }
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
        cancelButton.drawGameObject(sb);
        if (controller.isHost()){
            startButton.drawGameObject(sb);
        }
        font.getData().setScale(1);
        font.draw(sb, diffRounds,(float)avatars.get(0).getXPos()-30,(float)avatars.get(0).getYPos()+400);
        font.getData().setScale((float)1.5);
        this.drawPlayers(sb);

        font.draw(sb, "Game pin:", (float) avatars.get(0).getXPos() + 150, (float) avatars.get(0).getYPos() + 750);
        font.getData().setScale(3);
        font.draw(sb,String.valueOf(this.controller.getGameInfo().gameId), (float) avatars.get(0).getXPos()+110, (float) avatars.get(0).getYPos()+600);

        sb.end();
        super.renderStage();
    }

    @Override
    public void dispose() {
        cancelButton.getImage().dispose();
        startButton.getImage().dispose();
        for (int i = 0; i < this.avatars.size(); i++) {
            this.avatars.get(0).getImage().dispose();
        }
        this.font.dispose();
        this.background.getImage().dispose();
    }

    private void drawPlayers(SpriteBatch sb) {

        for (int i = 0; i < 4; i++) {
            try {
                for (Player player : controller.getGameInfo().players) {
                    avatars.get(player.getAvatarIndex()).setImage(avatarPics.get(player.getAvatarIndex()));
                }
                font.draw(sb, this.controller.getGameInfo().players.get(i).name, (float) avatars.get(i).getXPos() + 250, (float) avatars.get(i).getYPos() + 130);
            } catch (Exception e) {
                avatars.get(i).setImage(avatarPics.get(4));
            }
            avatars.get(i).drawGameObject(sb);
        }

    }
}
