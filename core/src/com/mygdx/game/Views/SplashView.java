package com.mygdx.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Controllers.SplashController;
import com.mygdx.game.Models.Dots;
import com.mygdx.game.Models.GameObject;
import com.mygdx.game.Models.Splash;

import java.util.ArrayList;
import java.util.List;

public class SplashView extends View {
    private Splash splash;
    private GameObject textPlaceholder;
    private float splashTimer = 0;
    private float colorTimer = 0;
    private int colorCounter = 0;
    private int frameCounter = 0;
    private int round;
    private int totalRounds;
    private BitmapFont font;
    private List<Integer> colorList = new ArrayList<>();

    private Dots dots;

    private SplashController controller;

    public SplashView(ViewManager vm) {
        super();
        this.controller = new SplashController(vm);
        this.colorList = controller.getColorInfo().getNumber();
        this.round = controller.getColorInfo().getRound();
        this.totalRounds = controller.getColorInfo().getMaxRounds();
        this.dots = new Dots(this.colorList);
        textPlaceholder = new GameObject(new Texture(Gdx.files.internal("splash.png")),0.33,0.1,1,false,false);
        font = new BitmapFont(Gdx.files.internal("bebaskai.fnt"));
        font.getData().setScale((float)1.5);
        splash = new Splash(new Texture(Gdx.files.internal("splash_1_blue.png")),0.5,0.6,3,true,true);
        this.splash.setFilePath(splash.getSplashes().get(colorList.get(this.colorCounter)).get(this.frameCounter));

    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        this.splashTimer+=dt;
        this.colorTimer+=dt;

        if (this.splashTimer>0.05 && this.frameCounter<4){
            this.frameCounter++;
            this.splash.setFilePath(splash.getSplashes().get(colorList.get(this.colorCounter)).get(this.frameCounter));
            this.splashTimer=0;
        }
        if (colorTimer>2 && this.colorCounter<(colorList.size()-1)){
            this.colorCounter++;
            this.dots.setDarkGreyDot(colorCounter);
            this.colorTimer=0;
            this.frameCounter=0;
        }
        if (colorTimer>2 && this.colorCounter==(colorList.size()-1)){
            controller.displayFinished(controller.getColorInfo().getGameId());
            controller.setAnswerView();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        splash.drawGameObject(sb);
        dots.drawDots(sb);
        font.draw(sb,"Round "+this.round+"/"+this.totalRounds, (float)textPlaceholder.getXPos(),(float)textPlaceholder.getYPos());
        sb.end();
    }

    @Override
    public void dispose() {

    }

    public List<Integer> getBackend(){
        return this.colorList;
    }

}

