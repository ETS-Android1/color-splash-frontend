package com.mygdx.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Controllers.SplashController;
import com.mygdx.game.Models.Dots;
import com.mygdx.game.Models.GameObject;
import com.mygdx.game.Models.Splash;
import com.mygdx.game.dataClasses.DisplayColors;

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
    private Sound splashSound;

    private Dots dots;

    private SplashController controller;

    public SplashView(ViewManager vm, DisplayColors colorinfo) {
        super();

        this.controller = new SplashController(vm, colorinfo);
        this.colorList = colorinfo.getNumber();
        this.round = colorinfo.getRound();
        this.totalRounds = colorinfo.getMaxRounds();
        this.splashSound = Gdx.audio.newSound(Gdx.files.internal("splash.mp3"));

        /*boolean loading = true;

        while(loading){
            loading= controller.isLoading();
            if(!loading){
                this.colorList = colorinfo.getNumber();
                this.round = colorinfo.getRound();
                this.totalRounds = colorinfo.getMaxRounds();
            }
        }*/
        System.out.println("colorInfo:"+colorinfo);

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
        if (this.colorTimer==0){
            this.splashSound.play();
        }

        this.splashTimer+=dt;
        this.colorTimer+=dt;

        if (this.splashTimer>0.05 && this.frameCounter<4){
            this.frameCounter++;
            this.splash.setFilePath(splash.getSplashes().get(colorList.get(this.colorCounter)).get(this.frameCounter));
            this.splashTimer=0;
        }
        if (colorTimer>0.9 && this.colorCounter<(colorList.size()-1)){
            this.colorCounter++;
            this.dots.setDarkGreyDot(colorCounter);
            this.colorTimer=0;
            this.frameCounter=0;
        }
        if (colorTimer>0.5 && this.colorCounter==(colorList.size()-1)){
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
        super.renderStage();
    }

    @Override
    public void dispose() {

    }

    public List<Integer> getBackend(){
        return this.colorList;
    }

}

