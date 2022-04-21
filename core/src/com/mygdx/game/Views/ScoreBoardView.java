package com.mygdx.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    private boolean isHost = true;


    private ArrayList<GameObject> avatars = new ArrayList<GameObject>();

    //List with playernumber from backend to match avatar
    private List<Integer> player_avatar = Arrays.asList(0,1,2,3);
    //Sorted score list from backend
    private List<String> players_sorted_score = Arrays.asList("Karen", "Ingrid", "Marius", "Fabian");




    protected ScoreBoardView(ViewManager vm) {
        super(vm);
        nextButton = new Button(new Texture("button_next.png"), 0.92, 0.08, 3,false, false);
        avatar1 = new GameObject(new Texture("avatar_orange.png"), 0.2, 0.6, 1,false,false);
        avatar2 = new GameObject(new Texture("avatar_green.png"), 0.2, 0.48, 1,false,false);
        avatar3 = new GameObject(new Texture("avatar_pink.png"), 0.2, 0.36, 1,false,false);
        avatar4 = new GameObject(new Texture("avatar_purple.png"), 0.2, 0.24, 1,false,false);
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
        nextButton.drawGameObject(sb);
        if (this.isHost){
            nextButton.drawGameObject(sb);
        }
        font.getData().setScale((float)1.5);
        this.drawscore(sb);
        font.draw(sb, "Game pin:", (float) avatar1.getXPos()+150, (float) avatar1.getYPos()+600);
        font.getData().setScale(3);
        //font.draw(sb,this.gamePin, (float) avatar1.getXPos()+110, (float) avatar1.getYPos()+450);
        sb.end();

    }

    @Override
    public void dispose() {

    }

    private void drawscore(SpriteBatch sb) {
        for (String player:players_sorted_score) {

        }
    }

}
