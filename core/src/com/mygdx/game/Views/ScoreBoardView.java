package com.mygdx.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Controllers.ScoreBoardController;
import com.mygdx.game.Models.Button;
import com.mygdx.game.Models.GameObject;
import com.mygdx.game.dataClasses.Result;
import com.mygdx.game.dataClasses.ScoreBoardInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScoreBoardView extends View{
    private BitmapFont font;
    private Button nextButton;
    private Button exitButton;

    private ScoreBoardController controller;

    private ArrayList<GameObject> avatars = new ArrayList<GameObject>();
    private List<String> avatarFiles = Arrays.asList("avatar_orange.png", "avatar_green.png", "avatar_pink.png", "avatar_purple.png");
    private List<String> trophy_avatar = Arrays.asList("trophy_orange.png","trophy_green.png", "trophy_pink.png", "trophy_purple.png");

    private ScoreBoardInfo scb;


    public ScoreBoardView(ViewManager vm, ScoreBoardInfo scoreBoardInfo) {
        super();
        controller = new ScoreBoardController(vm);
        this.scb = scoreBoardInfo;
        //controller.roundFinished();
        boolean loading = true;

        for (int i = 0; i < 4; i++) {
            avatars.add(new GameObject(new Texture(Gdx.files.internal("empty.png")), 0.05, 0.6 - 0.12 * i, 1, false, false));
        }

        while (loading) {
            loading = controller.isLoading();
            if (!loading) {
                for (Result result : this.scb.result) {
                    avatars.get(result.getAvatarIndex()).setFilePath(avatarFiles.get(result.getAvatarIndex()));
                }
            }
        }

        System.out.println("Fungerer");

        nextButton = new Button(new Texture("button_next.png"), 0.92, 0.08, 3,false, false);
        exitButton = new Button(new Texture("button_exit.png"), 0.92, 0.08, 3,false, false);
        font = new BitmapFont(Gdx.files.internal("bebaskai.fnt"));

    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            if (this.nextButton.isObjectClicked()) {
                dispose();
                controller.nextRound(this.scb.gameId);
                //controller.setGetReadyView();
            }
            if (exitButton.isObjectClicked()){
                dispose();
                controller.endGame(this.scb.gameId);
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
        if (controller.isHost() && !(this.scb.round==this.scb.maxRound)){
            nextButton.drawGameObject(sb);
        }
        font.getData().setScale((float)1.5);
        this.drawScore(sb);
        font.draw(sb, "Round: "+this.scb.round+"/"+this.scb.maxRound,(float) avatars.get(0).getXPos()+320,(float) avatars.get(0).getYPos()+400);
        font.getData().setScale(3);
        if(this.scb.round==this.scb.maxRound){
            font.draw(sb, "Final Score", (float) avatars.get(0).getXPos()+85, (float) avatars.get(0).getYPos()+600);
            exitButton.drawGameObject(sb);
        }
        else{
            font.draw(sb, "Scoreboard", (float) avatars.get(0).getXPos()+80, (float) avatars.get(0).getYPos()+600);
        }
        sb.end();
        super.renderStage();

    }

    @Override
    public void dispose() {
    }

    private void drawScore(SpriteBatch sb) {

        if (this.scb.round==this.scb.maxRound) {
            avatars.get(0).setFilePath(this.trophy_avatar.get(this.scb.result.get(0).getAvatarIndex()));
        }
        else{
            avatars.get(0).setFilePath(this.avatarFiles.get(this.scb.result.get(0).getAvatarIndex()));
        }
        for (int i=1  ; i<this.scb.result.size() ; i++){
            avatars.get(i).setFilePath(this.avatarFiles.get(this.scb.result.get(i).getAvatarIndex()));
        }
        for (int i = 0; i< this.scb.result.size() ; i++) {
            this.avatars.get(i).drawGameObject(sb);
            font.draw(sb, this.scb.result.get(i).getNickname(), (float) this.avatars.get(i).getXPos()+250, (float) this.avatars.get(i).getYPos()+130);
            font.draw(sb, Integer.toString(this.scb.result.get(i).getTotalScore()), (float) this.avatars.get(i).getXPos()+800, (float) this.avatars.get(i).getYPos()+130);
        }
    }

}
