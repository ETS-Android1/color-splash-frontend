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
    private final BitmapFont font;
    private Button nextButton;
    private Button exitButton;
    private final String roundString;

    private final ScoreBoardController controller;

    private final List<GameObject> avatars = new ArrayList<>();
    private final List<Texture> avatarFiles = new ArrayList<>(
            Arrays.asList(
                    new Texture(Gdx.files.internal("avatar_orange.png")),
                    new Texture(Gdx.files.internal("avatar_green.png")),
                    new Texture(Gdx.files.internal("avatar_pink.png")),
                    new Texture(Gdx.files.internal("avatar_purple.png"))
            )
    );
    private final List<Texture> trophy_avatar = new ArrayList<>(
            Arrays.asList(
                    new Texture(Gdx.files.internal("trophy_orange.png")),
                    new Texture(Gdx.files.internal("trophy_green.png")),
                    new Texture(Gdx.files.internal("trophy_pink.png")),
                    new Texture(Gdx.files.internal("trophy_purple.png"))
            )
    );

    public ScoreBoardView(ViewManager vm, ScoreBoardInfo scoreBoardInfo) {
        super();
        controller = new ScoreBoardController(vm, scoreBoardInfo);

        for (int i = 0; i < 4; i++) {
            avatars.add(new GameObject(new Texture(Gdx.files.internal("empty.png")), 0.05, 0.6 - 0.12 * i, 1, false, false));
        }

        for (Result result : this.controller.getScoreBoardInfo().getResult()) {
            avatars.get(result.getAvatarIndex()).setImage(avatarFiles.get(result.getAvatarIndex()));
        }

        this.roundString = "Round: "+this.controller.getScoreBoardInfo().getRound()+"/"+this.controller.getScoreBoardInfo().getMaxRound();

        if (this.controller.isHost() && !this.controller.isLastRound()) {
            nextButton = new Button(new Texture("button_next.png"), 0.92, 0.08, 3, false, false, vm);
        }
        if (this.controller.isLastRound()) {
            exitButton = new Button(new Texture("button_exit.png"), 0.92, 0.08, 3,false, false, vm);
        }
        font = new BitmapFont(Gdx.files.internal("bebaskai.fnt"));
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            if (controller.isHost() && !controller.isLastRound()) {
                if (this.nextButton.isObjectClicked() && this.controller.getScoreBoardInfo().getRound() != this.controller.getScoreBoardInfo().getMaxRound()) {
                    controller.nextRound(this.controller.getScoreBoardInfo().getGameId());
                }
            }
            if (this.controller.getScoreBoardInfo().getRound() == this.controller.getScoreBoardInfo().getMaxRound()) {
                if (exitButton.isObjectClicked() && this.controller.getScoreBoardInfo().getRound() == this.controller.getScoreBoardInfo().getMaxRound()) {
                    controller.endGame(this.controller.getScoreBoardInfo().getGameId());
                    controller.setMainMenuView();
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
        if (controller.isHost() && !(this.controller.getScoreBoardInfo().getRound()==this.controller.getScoreBoardInfo().getMaxRound())){
            nextButton.drawGameObject(sb);
        }
        font.getData().setScale((float)1.5);
        this.drawScore(sb);
        font.draw(sb, roundString,(float) avatars.get(0).getXPos()+320,(float) avatars.get(0).getYPos()+400);
        font.getData().setScale(3);
        if(this.controller.getScoreBoardInfo().getRound()==this.controller.getScoreBoardInfo().getMaxRound()){
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
        if (this.controller.isHost() && !this.controller.isLastRound()) {
            this.nextButton.getImage().dispose();
        }
        if (this.controller.isLastRound()) {
            this.exitButton.getImage().dispose();
        }
        this.font.dispose();
        this.background.getImage().dispose();
        for (GameObject avatar : this.avatars) {
            avatar.getImage().dispose();
        }
        super.dispose();
    }

    private void drawScore(SpriteBatch sb) {
        if (this.controller.getScoreBoardInfo().getRound()==this.controller.getScoreBoardInfo().getMaxRound()) {
            avatars.get(0).setImage(this.trophy_avatar.get(this.controller.getScoreBoardInfo().getResult().get(0).getAvatarIndex()));
        }
        else{
            avatars.get(0).setImage(this.avatarFiles.get(this.controller.getScoreBoardInfo().getResult().get(0).getAvatarIndex()));
        }
        for (int i=0  ; i<this.controller.getScoreBoardInfo().getResult().size() ; i++){
            if (i != 0) {
                avatars.get(i).setImage(this.avatarFiles.get(this.controller.getScoreBoardInfo().getResult().get(i).getAvatarIndex()));
            }
            this.avatars.get(i).drawGameObject(sb);
            font.draw(sb, this.controller.getScoreBoardInfo().getResult().get(i).getNickname(), (float) this.avatars.get(i).getXPos()+250, (float) this.avatars.get(i).getYPos()+130);
            font.draw(sb, Integer.toString(this.controller.getScoreBoardInfo().getResult().get(i).getTotalScore()), (float) this.avatars.get(i).getXPos()+800, (float) this.avatars.get(i).getYPos()+130);
        }
    }
}
