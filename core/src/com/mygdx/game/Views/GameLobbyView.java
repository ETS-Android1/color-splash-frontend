package com.mygdx.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Models.Button;
import com.mygdx.game.Models.GameObject;

import java.util.ArrayList;

public class GameLobbyView extends View {

    private boolean isHost = true;
    private String gamePin = "12345";
    private GameObject gamePinText;
    private GameObject avatar1;
    private GameObject avatar2;
    private GameObject avatar3;
    private GameObject avatar4;
    private ArrayList<GameObject> avatars = new ArrayList<GameObject>();
    private ArrayList<String> players = new ArrayList<String>();
    private Button cancelButton;
    private Button startButton;
    private BitmapFont font;
    private String difficulty = "medium";
    private String rounds = "8";

    protected GameLobbyView(ViewManager vm) {
        super(vm);
        startButton = new Button(new Texture("button_start.png"), 0.92, 0.08, 3,false, false);
        cancelButton = new Button(new Texture("button_cancel.png"), 0.08, 0.08, 3,false,false);
        //gamePinText = new GameObject(new Texture("button_cancel.png"), 0.4, 0.88, 1,false,false);
        avatar1 = new GameObject(new Texture("avatar_orange.png"), 0.2, 0.6, 1,false,false);
        avatar2 = new GameObject(new Texture("avatar_green.png"), 0.2, 0.48, 1,false,false);
        avatar3 = new GameObject(new Texture("avatar_pink.png"), 0.2, 0.36, 1,false,false);
        avatar4 = new GameObject(new Texture("avatar_purple.png"), 0.2, 0.24, 1,false,false);
        avatars.add(avatar1);
        avatars.add(avatar2);
        avatars.add(avatar3);
        avatars.add(avatar4);
        players.add("karen");
        players.add("ingrid");
        players.add("marius");
        players.add("fabian");
        font = new BitmapFont(Gdx.files.internal("bebaskai.fnt"));
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
        if (this.isHost){
            startButton.drawGameObject(sb);
        }
        font.getData().setScale(1);
        font.draw(sb, "Difficulty: "+this.difficulty+"    Rounds: "+this.rounds,(float)avatar1.getXPos()-30,(float)avatar1.getYPos()+400);
        font.getData().setScale((float)1.5);
        this.drawPlayers(sb);
        font.draw(sb, "Game pin:", (float) avatar1.getXPos()+200, (float) avatar1.getYPos()+750);
        font.getData().setScale(3);
        font.draw(sb,this.gamePin, (float) avatar1.getXPos()+150, (float) avatar1.getYPos()+650);
        sb.end();


    }

    @Override
    public void dispose() {
        cancelButton.getImage().dispose();
        startButton.getImage().dispose();


    }

    private void drawPlayers(SpriteBatch sb) {
        for (int i=0  ; i<players.size() ; i++) {
            this.avatars.get(i).drawGameObject(sb);
            font.draw(sb, this.players.get(i), (float) this.avatars.get(i).getXPos()+250, (float) this.avatars.get(i).getYPos()+130);
        }
    }
}
