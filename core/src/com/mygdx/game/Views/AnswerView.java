package com.mygdx.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Models.Button;
import com.mygdx.game.Models.Dots;
import com.mygdx.game.Models.GameObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnswerView extends View{

    private Button redButton;
    private Button blueButton;
    private Button yellowButton;
    private Button greenButton;
    private GameObject timerBackground;
    private Dots dots;
    private Integer timer;
    private List<Integer> correctColors = Arrays.asList(0,1,2,3,0,1,1,2);



    private float timeCount;
    private BitmapFont font;
    private int buttonCount = 0;
    private boolean playerFinished = false;
    private double finishedTime = 0;
    private String feedback = "";
    private List<Integer> playerAnswer = new ArrayList<>();
    private int localScore = 0;

    protected AnswerView(ViewManager vm) {
        super(vm);
        redButton = new Button(new Texture(Gdx.files.internal("button_red.png")),0.1,0.24,3,false,false);
        blueButton = new Button(new Texture(Gdx.files.internal("button_blue.png")),0.1,0.1,3,false,false);
        greenButton = new Button(new Texture(Gdx.files.internal("button_green.png")),0.9,0.24,3,false,false);
        yellowButton = new Button(new Texture(Gdx.files.internal("button_yellow.png")),0.9,0.1,3,false,false);
        timerBackground = new GameObject(new Texture(Gdx.files.internal("splash_grey.png")),1,0.5,2.5,false,true);
        dots = new Dots();
        timer = 8;
        timeCount = 0;
        font = new BitmapFont(Gdx.files.internal("bebaskai.fnt"));
        dots.getDots().get(0).setFilePath("circle_lightgrey.png");


    }


    @Override
    protected void handleInput() {

        if (Gdx.input.justTouched() && !this.playerFinished) {

            if (redButton.isObjectClicked()){
                this.dots.getDots().get(buttonCount).setFilePath("circle_red.png");
                this.playerAnswer.add(1);
                this.buttonCount++;
            }
            if (greenButton.isObjectClicked()){
                this.dots.getDots().get(buttonCount).setFilePath("circle_green.png");
                this.playerAnswer.add(2);
                this.buttonCount++;
            }
            if (blueButton.isObjectClicked()){
                this.dots.getDots().get(buttonCount).setFilePath("circle_blue.png");
                this.playerAnswer.add(0);
                this.buttonCount++;
            }
            if (yellowButton.isObjectClicked()){
                this.dots.getDots().get(buttonCount).setFilePath("circle_yellow.png");
                this.playerAnswer.add(3);
                this.buttonCount++;
            }

            if(this.buttonCount==this.dots.getDots().size()){
                //FIRE EVENT TO BACKEND
                this.playerFinished=true;
                this.finishedTime = this.timer;
                if(this.finishedTime > 6){
                    this.feedback = "Too fast..?";
                }
                else if(this.finishedTime > 4){
                    this.feedback = "  Speedy!!";
                }
                else if(this.finishedTime > 2) {
                    this.feedback = "Too slow..?";
                }
            }
        }


    }

    @Override
    public void update(float dt) {
        handleInput();
        this.timeCount += dt;
        if (this.timeCount >= 1) {
            this.timer--;
            this.timeCount = 0;
        }
        if(this.timer==0){
            this.playerFinished=true;
            this.feedback="Time's up!";
            this.gameFinished();
        }
        if(this.timer==-3){
            System.out.println(this.playerAnswer);
            vm.set(new ScoreBoardView(vm));
        }

    }

    @Override
    public void render(SpriteBatch sb){
        super.render(sb);
        redButton.drawGameObject(sb);
        blueButton.drawGameObject(sb);
        greenButton.drawGameObject(sb);
        yellowButton.drawGameObject(sb);
        dots.drawDots(sb);
        if (this.playerFinished) {
            this.font.getData().setScale(2);
            this.font.draw(sb,feedback,(float)(timerBackground.getXPos())+100,(float)(timerBackground.getYPos()-100));
            this.timerBackground.drawGameObject(sb);
            if(this.timer>0) {
                this.font.getData().setScale(3);
                this.font.draw(sb, timer.toString(), (float) (timerBackground.getXPos()) + 310, (float) (timerBackground.getYPos() + 380));
            }
            else{
                this.font.draw(sb, ""+this.localScore+"/"+this.correctColors.size(), (float) (timerBackground.getXPos()) + 270, (float) (timerBackground.getYPos() + 350));
            }
        }
        if (!this.playerFinished){
            this.font.getData().setScale(3);
            this.timerBackground.drawGameObject(sb);
            this.font.draw(sb,timer.toString(),(float)(timerBackground.getXPos())+310,(float)(timerBackground.getYPos()+380));
        }
        sb.end();
    }

    @Override
    public void dispose() {

    }

    public void gameFinished() {
        this.localScore = 0;
        for (int i = 0; i<this.correctColors.size(); i ++) {
            try {
                if (this.correctColors.get(i) == this.playerAnswer.get(i)) {
                    this.localScore++;
                    this.dots.getDots().get(i).setFilePath("result_right.png");
                }
                else {
                    this.dots.getDots().get(i).setFilePath("result_wrong.png");
                }
            }
            catch (IndexOutOfBoundsException e) {
                this.dots.getDots().get(i).setFilePath("result_wrong.png");
            }
        }
    }
}
