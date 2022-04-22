package com.mygdx.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Controllers.ScoreBoardController;
import com.mygdx.game.Models.Button;
import com.mygdx.game.Models.GameObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScoreBoardView extends View{

    private GameObject avatar1;
    private GameObject avatar2;
    private GameObject avatar3;
    private GameObject avatar4;
    private BitmapFont font;
    private Button nextButton;
    private Button exitButton;
    private ScoreBoardController controller;
    private List<String> avatarFiles = Arrays.asList("avatar_orange.png", "avatar_green.png", "avatar_pink.png", "avatar_purple.png");


    private ArrayList<GameObject> avatars = new ArrayList<GameObject>();

    private boolean isHost = true;
    //List with playernumber from backend to match avatar
    private List<Integer> player_avatar = Arrays.asList(1,2,0,3);
    //Sorted score list from backend
    private List<String> players = Arrays.asList("Karen", "Ingrid", "Marius", "Fabian");
    private List<String> score = Arrays.asList("344", "2110", "56", "1");
    private int round = 4;
    private int maxRound = 4;




    public ScoreBoardView(ViewManager vm) {
        super();
        this.controller = new ScoreBoardController(vm);
        nextButton = new Button(new Texture("button_next.png"), 0.92, 0.08, 3,false, false);
        exitButton = new Button(new Texture("button_exit.png"), 0.92, 0.08, 3,false, false);
        avatar1 = new GameObject(new Texture("avatar_orange.png"), 0.05, 0.6, 1,false,false);
        avatar2 = new GameObject(new Texture("avatar_orange.png"), 0.05, 0.48, 1,false,false);
        avatar3 = new GameObject(new Texture("avatar_orange.png"), 0.05, 0.36, 1,false,false);
        avatar4 = new GameObject(new Texture("avatar_orange.png"), 0.05, 0.24, 1,false,false);
        avatars.add(avatar1);
        avatars.add(avatar2);
        avatars.add(avatar3);
        avatars.add(avatar4);
        font = new BitmapFont(Gdx.files.internal("bebaskai.fnt"));
        font.setColor(Color.WHITE);

    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            if (this.nextButton.isObjectClicked()) {
                dispose();
                controller.setGetReadyView();
            }
            if (exitButton.isObjectClicked()){
                dispose();
                controller.setMainMenuView();
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
        if (this.isHost && !(this.round==this.maxRound)){
            nextButton.drawGameObject(sb);
        }
        font.getData().setScale((float)1.5);
        this.drawscore(sb);
        font.draw(sb, "Round: "+this.round+"/"+this.maxRound,(float) avatar1.getXPos()+320,(float) avatar1.getYPos()+400);
        font.getData().setScale(3);
        if(this.round==this.maxRound){
            font.draw(sb, "Final Score", (float) avatar1.getXPos()+85, (float) avatar1.getYPos()+600);
            exitButton.drawGameObject(sb);
        }
        else{
            font.draw(sb, "Scoreboard", (float) avatar1.getXPos()+80, (float) avatar1.getYPos()+600);
        }
        sb.end();

    }

    @Override
    public void dispose() {
    }

    private void drawscore(SpriteBatch sb) {
        for (int i=0  ; i<player_avatar.size() ; i++){
            avatars.get(i).setFilePath(this.avatarFiles.get(player_avatar.get(i)));
        }
        for (int i = 0; i< players.size() ; i++) {
            this.avatars.get(i).drawGameObject(sb);
            font.draw(sb, this.players.get(i), (float) this.avatars.get(i).getXPos()+250, (float) this.avatars.get(i).getYPos()+130);
            font.draw(sb, this.score.get(i), (float) this.avatars.get(i).getXPos()+800, (float) this.avatars.get(i).getYPos()+130);
        }
    }

}
