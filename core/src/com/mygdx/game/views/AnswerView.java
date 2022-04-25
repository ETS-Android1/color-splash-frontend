package com.mygdx.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.controllers.AnswerController;
import com.mygdx.game.views.gameObjects.Button;
import com.mygdx.game.views.gameObjects.Dots;
import com.mygdx.game.views.gameObjects.GameObject;
import com.mygdx.game.models.DisplayColors;

import java.util.List;

public class AnswerView extends View{

    private final Button redButton;
    private final Button blueButton;
    private final Button yellowButton;
    private final Button greenButton;
    private final GameObject timerBackground;
    private final Dots dots;
    private final AnswerController controller;

    private final Texture circleRed = new Texture(Gdx.files.internal("circle_red.png"));
    private final Texture circleGreen = new Texture(Gdx.files.internal("circle_green.png"));
    private final Texture circleBlue = new Texture(Gdx.files.internal("circle_blue.png"));
    private final Texture circleYellow = new Texture(Gdx.files.internal("circle_yellow.png"));
    private final Texture right = new Texture(Gdx.files.internal("result_right.png"));
    private final Texture wrong = new Texture(Gdx.files.internal("result_wrong.png"));

    private final BitmapFont font;

    public AnswerView(ViewManager vm, DisplayColors gameInfo) {
        super();
        this.controller = new AnswerController(vm, gameInfo);
        redButton = new Button(new Texture(Gdx.files.internal("button_red.png")),0.1,0.24,3,false,false, vm);
        blueButton = new Button(new Texture(Gdx.files.internal("button_blue.png")),0.1,0.1,3,false,false, vm);
        greenButton = new Button(new Texture(Gdx.files.internal("button_green.png")),0.9,0.24,3,false,false, vm);
        yellowButton = new Button(new Texture(Gdx.files.internal("button_yellow.png")),0.9,0.1,3,false,false, vm);
        timerBackground = new GameObject(new Texture(Gdx.files.internal("splash_grey.png")),1,0.5,2.5,false,true);

        dots = new Dots(this.controller.getCorrectColors());
        font = new BitmapFont(Gdx.files.internal("bebaskai.fnt"));
        Texture circleLightGrey = new Texture(Gdx.files.internal("circle_lightgrey.png"));
        dots.getDots().get(0).setImage(circleLightGrey);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched() && !this.controller.isPlayerFinished()) {
            if (redButton.isObjectClicked()){
                this.dots.getDots().get(this.controller.redButtonClicked()).setImage(circleRed);
            }
            if (greenButton.isObjectClicked()){
                this.dots.getDots().get(this.controller.greenButtonClicked()).setImage(circleGreen);
            }
            if (blueButton.isObjectClicked()){
                this.dots.getDots().get(this.controller.blueButtonClicked()).setImage(circleBlue);
            }
            if (yellowButton.isObjectClicked()){
                this.dots.getDots().get(this.controller.yellowButtonClicked()).setImage(circleYellow);
            }

            this.controller.setPlayerFinished(this.dots.getDots().size());

        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        this.controller.updateTimeCount(dt);
        if (this.controller.getTimer() == 0) {
            List<Boolean> answers = this.controller.setAnswers();
            for (int i = 0; i < answers.size(); i++) {
                this.dots.getDots().get(i).setImage(answers.get(i) ? right : wrong);
            }
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
        if (this.controller.isPlayerFinished()) {
            this.font.getData().setScale(2);
            this.font.draw(sb,this.controller.getFeedback(),(float)(timerBackground.getXPos())+100,(float)(timerBackground.getYPos()-100));
            this.timerBackground.drawGameObject(sb);
            if(this.controller.getTimer()>0) {
                this.font.getData().setScale(3);
                this.font.draw(sb, this.controller.getTimer().toString(), (float) (timerBackground.getXPos()) + 310, (float) (timerBackground.getYPos() + 380));
            }
            else{
                this.font.draw(sb, ""+this.controller.getLocalScore()+"/"+this.controller.getCorrectColors().size(), (float) (timerBackground.getXPos()) + 270, (float) (timerBackground.getYPos() + 350));
            }
        } else {
            this.font.getData().setScale(3);
            this.timerBackground.drawGameObject(sb);
            this.font.draw(sb,this.controller.getTimer().toString(),(float)(timerBackground.getXPos())+310,(float)(timerBackground.getYPos()+380));
        }
        sb.end();
        super.renderStage();
    }

    @Override
    public void dispose() {
        super.dispose();
        for (int i = 0; i < this.dots.getDots().size(); i++) {
            this.dots.getDots().get(i).getImage().dispose();
        }
        this.blueButton.getImage().dispose();
        this.greenButton.getImage().dispose();
        this.redButton.getImage().dispose();
        this.yellowButton.getImage().dispose();
        this.timerBackground.getImage().dispose();
        this.font.dispose();
    }


}
