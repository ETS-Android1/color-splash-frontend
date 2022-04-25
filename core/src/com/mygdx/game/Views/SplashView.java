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

import java.util.List;

public class SplashView extends View {
    private final Splash splash;
    private final GameObject textPlaceholder;
    private float splashTimer = 0;
    private float colorTimer = 0;
    private int colorCounter = 0;
    private int frameCounter = 0;
    private final BitmapFont font;
    private final List<Integer> colorList;
    private final String roundsString;
    private final Sound splashSound;

    private final Dots dots;

    private final SplashController controller;

    public SplashView(ViewManager vm, DisplayColors colorInfo) {
        super();

        this.controller = new SplashController(vm, colorInfo);
        this.colorList = colorInfo.getNumber();
        int round = colorInfo.getRound();
        int totalRounds = colorInfo.getMaxRounds();
        this.roundsString = "Round "+ round +"/"+ totalRounds;
        this.splashSound = Gdx.audio.newSound(Gdx.files.internal("splash.mp3"));

        this.dots = new Dots(this.colorList);
        textPlaceholder = new GameObject(new Texture(Gdx.files.internal("splash.png")),0.33,0.1,1,false,false);
        font = new BitmapFont(Gdx.files.internal("bebaskai.fnt"));
        font.getData().setScale((float)1.5);
        splash = new Splash(new Texture(Gdx.files.internal("splash_1_blue.png")),0.5,0.6,3,true,true);
        this.splash.setFilePath(splash.getSplashes().get(colorList.get(this.colorCounter)).get(this.frameCounter));
    }

    @Override
    public void update(float dt) {
        if (this.colorTimer==0 && controller.isSound()){
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
        font.draw(sb, roundsString, (float)textPlaceholder.getXPos(), (float)textPlaceholder.getYPos());
        sb.end();
        super.renderStage();
    }

    @Override
    public void dispose() {
        super.dispose();
        for (GameObject dot : this.dots.getDots()) {
            dot.getImage().dispose();
        }
        this.font.dispose();
        this.splash.getImage().dispose();
        this.textPlaceholder.getImage().dispose();
        this.splashSound.dispose();
    }
}

